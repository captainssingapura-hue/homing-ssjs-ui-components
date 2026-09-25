// =============================================================================
// FloatLayer — the floor the panes float on: what was the Desk, before the desk
// became the whole — the register, the docks, this layer and the moves between
// them (RFC 0066 E3, appendix "tab-panes"). A branch component: the caller makes a
// sub-branch for it and hands it in; the desk mints its floor on the host and
// a sub-branch per pane. It owns the stack: z-order, the active one, the host
// they float in. The workspace's substrate.
//
//   new FloatLayer(branch, { host, layer?, onEvent?, minW?, minH?, onDragMove?, onDragEnd?, keyboard?, keyboardId? })
//     host   a flex box; the desk is its item and fills it — or, with layer,
//            a positioned box the desk lies over, the hand passing through it
//            except on a pane: a desk over docks.
//     keyboard  the page's KeyboardSteward: the desk joins the keyboard party as
//            keyboardId (its branch's name, unless said) and claims by the
//            convention on its floor — a press or the focus arriving in any pane.
//            It holds the keys for the panes: a key goes to the active pane's
//            widget by its key(ev), when it has one, then Escape closes the
//            active pane if it can be closed. The widgets are built without a
//            steward of their own. No keydown listener of its own.
//
//   layer.open({ id?, title, icon?, head?, offered?, x?, y?, w?, h?, closable?, widget?, params? })  → the pane
//       head: false, a frame with no head, for a holder that gives it a handle of its own;
//       offered: false, its drags are not the desk's onDragMove/onDragEnd's to watch — a
//       frame that is not one tab, a float of many, is never offered to a dock;
//       onDragMove, onDragEnd: its own, in place of the desk's, for a holder that decides
//       while it moves whether it is offered at all
//       id defaults to "pane-N"; x, y cascade when not given. The id is the
//       holder's name for the pane — a tab's own id, any string; the pane's
//       branch is the desk's, "float-" and a fresh uuid at every opening, and
//       is never made from the id. `widget` is a
//       class by the base's contract — new widget(branch, params) → root,
//       setActive?, dispose? — or an instance already made, a tab's, whose
//       branch is its holder's; its root goes in the pane's body. Reports
//       Opened, then Raised, since a new pane is the active one.
//   layer.release(id)    → { id, title, icon, widget, closable }: the tab leaves the desk
//                         for a dock, widget and icon and all, NOT disposed; the
//                         frame goes; Released(id), and the next on the stack raised
//   layer.raise(id)      → Raised(id) when it was not already on top; the one
//                         leaving is told setActive(false), the one coming in
//                         setActive(true), pane and widget both
//   layer.close(id)      → the widget disposed, the pane dissolved, Closed(id);
//                         the next on the stack raised after
//   layer.pane(id) .panes() (ids, bottom to top) .active() .has(id)
//   layer.root           the floor
//   layer.key(ev)        a keydown from whoever holds the keys for the desk: the active pane's
//                       widget first, then Escape; true when taken
//   layer.dispose()      every pane closed in order, the floor removed, the branch dissolved
//
// The hand: a press anywhere on a pane raises it; focus into it raises it;
// Escape, through the party, closes the active one if it can be closed; the cross closes
// it. A pane's own Moved and Resized come through the same sink; a pane's
// drag is watched through onDragMove(pane, x, y) and onDragEnd(pane, x, y,
// ok), for a holder that offers it to a dock. Every mutation is one
// FloatEvents object on one sink, onEvent(ev).
// =============================================================================

const _deskOwner = Object.freeze({ toString: () => "desk" });
var _CASCADE = 28;

class FloatLayer {
    constructor(branch, opts) {
        if (!branch) throw new Error("[FloatLayer] a branch of its own is required");
        if (!opts || !opts.host) throw new Error("[FloatLayer] opts.host is required");
        branch.activate(_deskOwner);
        this.branch = branch;
        this._sink = typeof opts.onEvent === "function" ? opts.onEvent : null;
        this._minW = opts.minW;
        this._minH = opts.minH;
        this._onDragMove = typeof opts.onDragMove === "function" ? opts.onDragMove : null;
        this._onDragEnd = typeof opts.onDragEnd === "function" ? opts.onDragEnd : null;
        this._panes = new Map();          // id → { pane, widget, closable }
        this._order = [];                 // ids, bottom to top
        this._top = 0;
        this._active = null;
        this._n = 0;
        var root = branch.createElement("desk", "div");
        css.addClass(root, opts.layer ? fp_desk_layer : fp_desk);
        opts.host.appendChild(root);
        this.root = root;
        // the keys: through the party, when the desk is handed the steward
        this._kb = null; this._kbId = null; this._offKeys = null;
        if (opts.keyboard) {
            var self = this;
            this._kb = opts.keyboard;
            this._kbId = this._kb.join(opts.keyboardId != null ? String(opts.keyboardId) : branch.name, { keyDown: function (ev) { return self.key(ev); } });
            this._offKeys = Keys.claimOn(root, this._kb, this._kbId);
        }
    }

    /** A keydown from whoever holds the keys for the desk: the active pane's widget first, then Escape closes the active pane when it can be closed; true when taken. */
    key(ev) {
        if (!ev || this._active === null) return false;
        var entry = this._panes.get(this._active);
        if (entry.widget && typeof entry.widget.key === "function" && entry.widget.key(ev)) return true;
        if (ev.key === "Escape" && entry.closable) { this.close(this._active); return true; }
        return false;
    }

    open(spec) {
        var self = this;
        var s = spec || {};
        var id = s.id == null ? "pane-" + (++this._n) : String(s.id);
        if (this._panes.has(id)) throw new Error("[FloatLayer] a pane is already open as '" + id + "'");
        var k = this._order.length % 8;
        var paneBranch = this.branch.createBranch(FloatLayer._freshName());
        var pane = new FloatingPane(paneBranch, {
            id: id, title: s.title == null ? id : s.title, icon: s.icon || null, head: s.head,
            x: s.x == null ? 24 + _CASCADE * k : s.x, y: s.y == null ? 24 + _CASCADE * k : s.y,
            w: s.w, h: s.h, z: ++this._top, closable: s.closable !== false,
            minW: this._minW, minH: this._minH,
            onEvent: function (ev) { self._fire(ev); },
            onClose: function () { self.close(id); },
            onDragMove: s.offered === false ? null : (s.onDragMove || this._onDragMove),
            onDragEnd: s.offered === false ? null : (s.onDragEnd || this._onDragEnd)
        });
        this.root.appendChild(pane.root);
        var entry = { pane: pane, widget: null, closable: s.closable !== false };
        if (typeof s.widget === "function") entry.widget = new s.widget(paneBranch.createBranch("widget"), s.params || {});
        else if (s.widget && typeof s.widget === "object" && s.widget.root) entry.widget = s.widget;
        if (entry.widget) pane.body.appendChild(entry.widget.root);
        pane.root.addEventListener("pointerdown", function () { self.raise(id); });
        pane.root.addEventListener("focusin", function () { self.raise(id); });
        this._panes.set(id, entry);
        this._order.push(id);
        var b = pane.bounds();
        this._fire(FloatEvents.Opened(id, pane.title(), b.x, b.y, b.w, b.h));
        this._activate(id, true);
        return pane;
    }

    raise(id) {
        var entry = this._panes.get(id);
        if (!entry) throw new Error("[FloatLayer] no pane '" + id + "'");
        if (this._active === id) return this;
        var i = this._order.indexOf(id);
        if (i >= 0) { this._order.splice(i, 1); this._order.push(id); }
        entry.pane.raise(++this._top);
        this._activate(id, true);
        return this;
    }

    close(id) {
        var entry = this._panes.get(id);
        if (!entry) return null;
        if (this._active === id) { this._tell(entry, false); this._active = null; }
        if (entry.widget && typeof entry.widget.dispose === "function") { try { entry.widget.dispose(); } catch (e) { console.error("[FloatLayer] widget dispose failed", e); } }
        var root = entry.pane.root;
        if (root.parentNode) root.parentNode.removeChild(root);
        entry.pane.dispose();
        this._panes.delete(id);
        var i = this._order.indexOf(id);
        if (i >= 0) this._order.splice(i, 1);
        this._fire(FloatEvents.Closed(id));
        if (this._active === null && this._order.length) this._activate(this._order[this._order.length - 1], true);
        return entry.pane;
    }

    /** The tab leaves the desk for a dock: the frame goes, the widget travels on, not disposed. */
    release(id) {
        var entry = this._panes.get(id);
        if (!entry) return null;
        var tab = { id: id, title: entry.pane.title(), icon: entry.pane.icon(), widget: entry.widget, closable: entry.closable };
        if (entry.widget && entry.widget.root.parentNode === entry.pane.body) entry.pane.body.removeChild(entry.widget.root);
        entry.pane.icon(null);   // the icon is the holder's, and goes with the tab, not with the frame
        if (this._active === id) { entry.pane.setActive(false); this._active = null; }
        var root = entry.pane.root;
        if (root.parentNode) root.parentNode.removeChild(root);
        entry.pane.dispose();
        this._panes.delete(id);
        var i = this._order.indexOf(id);
        if (i >= 0) this._order.splice(i, 1);
        this._fire(FloatEvents.Released(id));
        if (this._active === null && this._order.length) this._activate(this._order[this._order.length - 1], true);
        return tab;
    }

    /**
     * A pane's branch's name: the desk's own, fresh at every opening, so no
     * holder's id — a tab's, which may be any string — is ever asked to be a
     * name on the DOM party, and no name is ever used twice.
     */
    static _freshName() { return "float-" + FloatLayer._uuid(); }

    /** RFC 4122 v4: crypto.randomUUID where the page is a secure context, else the same from getRandomValues. */
    static _uuid() {
        if (typeof crypto.randomUUID === "function") return crypto.randomUUID();
        var b = crypto.getRandomValues(new Uint8Array(16)), h = "";
        b[6] = (b[6] & 0x0f) | 0x40;
        b[8] = (b[8] & 0x3f) | 0x80;
        for (var i = 0; i < 16; i++) h += (b[i] + 0x100).toString(16).slice(1);
        return h.slice(0, 8) + "-" + h.slice(8, 12) + "-" + h.slice(12, 16) + "-" + h.slice(16, 20) + "-" + h.slice(20);
    }

    pane(id) { var e = this._panes.get(id); return e ? e.pane : null; }
    has(id) { return this._panes.has(id); }
    panes() { return this._order.slice(); }
    active() { return this._active; }

    dispose() {
        var self = this;
        this._order.slice().forEach(function (id) { self.close(id); });
        if (this._offKeys) { this._offKeys(); this._offKeys = null; }
        if (this._kb) { this._kb.leave(this._kbId); this._kb = null; }
        if (this.root.parentNode) this.root.parentNode.removeChild(this.root);
        try { this.branch.dissolve(); } catch (e) {}
    }

    // ── the active one ──────────────────────────────────────────────────────
    _activate(id, report) {
        if (this._active === id) return;
        if (this._active !== null) { var prev = this._panes.get(this._active); if (prev) this._tell(prev, false); }
        this._active = id;
        this._tell(this._panes.get(id), true);
        if (report) this._fire(FloatEvents.Raised(id));
    }

    _tell(entry, on) {
        entry.pane.setActive(on);
        if (entry.widget && typeof entry.widget.setActive === "function") { try { entry.widget.setActive(on); } catch (e) { console.error("[FloatLayer] widget setActive failed", e); } }
    }

    _fire(ev) { if (this._sink) this._sink(ev); }
}
