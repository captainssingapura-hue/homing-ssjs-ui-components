// =============================================================================
// Desk — the whole (RFC 0066 E3, appendix "tab-panes", §3): the register of a
// desk's tab-panes, the hosts that hold them — DOCKS, the multi-tab panes the
// page places where it likes, and FLOATS, which are the desk's own — the float
// layer the floats lie on, and every move of a tab-pane between them. A tab-
// pane is opened in the desk's register and owned there for its whole life; a
// host holds it and never owns it. A lone multi-tab pane is a desk with one
// host: the float layer is made only when a float is first wanted.
//
//   new Desk(branch, { host, budget?, onEvent?, focus?, focusName?, menus?, keyboard?, keyboardId?, minW?, minH? })
//     branch   the desk's own, handed unactivated
//     budget   the most tabs the desk holds at once — THE limit: a pane has none of its own,
//              its bar scrolls. Spent, an open is refused and every dock's plus is greyed;
//              none, and there is no limit
//     host     the positioned box its docks sit in; the float layer lies over
//              it, the hand passing through except on a frame
//     focus    the desk's focus branch: its docks under it, and `resting`, desk.rest,
//              where a tab-pane's widget RESTS while no host holds it — apart from
//              the docks, whose names are the page's. One of its own under the
//              page's root unless said, named focusName, the branch's name unless said
//     menus    the page's ContextMenuSteward, handed to its floats, so a chip
//              afloat asks for its menu as a chip in a dock does; and the TAB MENU
//              is answered here, the pane's own kind on any chip of the desk's -
//              Detach floats the tab under its chip, Close closes it; a tab afloat
//              is detached already, so Detach is off there. A steward holds one
//              answer a kind: one desk to a steward
//     onEvent  its reports: a tab-pane's arrival (TabAdded) and every move
//              between hosts (TabMoved), a float's host's reports, the layer's
//
//   desk.register          the TabRegister: every tab-pane of this desk is opened there
//   desk.layer             the FloatLayer, made when first wanted
//   desk.addDock(pane) .removeDock(pane) .docks()
//                          a multi-tab pane becomes a host of the desk: a tab-pane dragged over
//                          its strip is offered to it, and its focus branch goes under the desk's,
//                          so the desk holds its panes in the focus tree too; removed, it goes back
//                          where it was. docks() are the docks and the floats' hosts; only
//                          the docks are ever offered a tab
//   desk.open({ id?, title?, icon?, pinned?, closable?, make }, host, index?, how?) → the TabPane:
//                          opened in the register — its widget made by make(branch, tab) — and
//                          put in the host, which says it arrived (TabAdded). how: "quiet", the
//                          host's own rule on what shows; "front", shown; "focus", shown and its
//                          widget handed the keys. "quiet" unless said
//   desk.move(tp, to, index?) → the index it took: from the host it is in to another, whole or
//                          not at all — refused before anything leaves when that host would not
//                          take it. One TabMoved; a tab-pane in no host arrives, one TabAdded
//   desk.unmount(tp)       → the host it left, or null when none held it: the tab-pane in no host,
//                          open still in the register, its widget resting in the desk. Nothing is
//                          reported: an unmount is its owner's, who says it - the host did not
//                          start it. A float it leaves empty goes, as after any last tab
//   desk.show(tp)          → its host, or null when none holds it: the tab-pane shown where it is —
//                          its host shows it, a float holding it raised. The keys are not given
//                          here: that is the caller's, and only ever at the user's asking
//                          (RFC 0066 E3, keyboard §17.2)
//   desk.detach(tp, at?)   → the float: the tab-pane into a float of its own, its bar at the
//                          point - under its own chip unless said - within the desk: the
//                          tab menu's Detach, a dock's Shift+↓
//   desk.float(opts?)      → a Floater: a frame around a single-tab pane of its own, its one bar
//                          the tab's chip. Named opts.id, else "float-N": a name no float on the desk
//                          has - a float brought back under its old name keeps it, and the next is
//                          named past it. A FLOAT IS ONE TAB IN TRANSIT, the desk's temporary vehicle
//                          and never a second dock: it is never offered a tab - a move into a float
//                          that carries one is refused - and its tab is the tab in the hand: dragged
//                          by its bar, it is offered to the docks it passes, and let go over a strip
//                          the tab-pane lands there, the one the dock shows - the hand put it there
//                          to look at it - and the float, empty, is gone
//   desk.floats()          → the floats, bottom of the stack first: none while the layer is not made
//   desk.dispose()         every tab-pane closed, the floats with them; the layer; the docks'
//                          focus branches back where they were, for the docks are the page's;
//                          the desk's own focus branch left
//
// THE KEYBOARD WALK PASSES THE DESK BY (inWalk() false): the desk is no Tab stop, and
// nothing in it is — not a pane, not what a pane shows, not what rests in the desk.
// Between its panes the desk will move a way of its own, a mode it is put in; until
// then a press selects.
// =============================================================================

const _deskOwner = Object.freeze({ toString: () => "desk" });
var _GRIP_X = 60, _GRIP_Y = 14;      // where the bar sits under the point a float is opened at
var _FLOAT_W = 320, _FLOAT_H = 220;
var _HOW = Object.freeze(["quiet", "front", "focus"]);

class Desk {
    constructor(branch, opts) {
        if (!branch) throw new Error("[Desk] a branch of its own is required");
        if (!opts || !opts.host) throw new Error("[Desk] opts.host is required");
        branch.activate(_deskOwner);
        this.branch = branch;
        this._host = opts.host;
        this._opts = opts;
        this._sink = typeof opts.onEvent === "function" ? opts.onEvent : null;
        this._menus = opts.menus || null;
        this._ownFocus = !opts.focus;
        this.focus = opts.focus || focusParty.root.createBranch(opts.focusName != null ? String(opts.focusName) : branch.name, this);
        this.rest = this.focus.createBranch("resting", this);
        var self = this;
        this.register = new TabRegister(branch.createBranch("tabs"), { focus: this.rest, budget: opts.budget, onCount: function () { self._roomed(); } });
        this._layer = null;
        this._docks = [];
        this._homes = new Map();      // a dock → the focus branch it was in before the desk took it under its own
        this._floaters = new Map();   // a float's host → the float, while it lasts
        this._floats = 0;             // float-1, float-2 and on: never a name another float of this desk had
        this._target = null;
        this._index = -1;
        this._disposed = false;
        if (this._menus) this._answerTabMenu(this._menus);
    }

    /**
     * The tab menu, the pane's own kind, on any chip of the desk's hosts: Detach floats the tab under its chip, Close
     * closes it; a pinned tab, or one that will not close, is offered neither, and a tab afloat is not offered Detach.
     */
    _answerTabMenu(menus) {
        var self = this;
        menus.handle(MultiTabPane.MENU, {
            pick: function (id, o) {
                if (id === "detach" && !self._floaters.has(o.pane)) self.detach(o.tab);
                else if (id === "close" && !o.tab.requestClose()) o.pane.removeTab(o.tab.id);
            },
            state: function (id, o) { return { disabled: !!o.tab.pinned || o.tab.closable === false || (id === "detach" && self._floaters.has(o.pane)) }; }
        });
    }

    get layer() {
        if (!this._layer) {
            var self = this, o = this._opts;
            this._layer = new FloatLayer(this.branch.createBranch("layer"), {
                host: this._host, layer: true, minW: o.minW, minH: o.minH, keyboard: o.keyboard, keyboardId: o.keyboardId,
                onEvent: function (ev) { self._fire(ev); }
            });
        }
        return this._layer;
    }

    addDock(pane) {
        if (this._docks.indexOf(pane) >= 0) return this;
        var m = pane.focus ? pane.focus.owner : null;
        if (m && m.in && m.in !== this.focus) { this._homes.set(pane, m.in); this.focus.adopt(m); }
        this._docks.push(pane);
        if (typeof pane.setRoom === "function") pane.setRoom(this.register.room() > 0);
        return this;
    }
    removeDock(pane) {
        var i = this._docks.indexOf(pane);
        if (i < 0) return this;
        this._docks.splice(i, 1);
        this._home(pane);
        if (typeof pane.setRoom === "function") pane.setRoom(true);   // no longer this desk's to say
        return this;
    }
    docks() { return this._docks.slice(); }

    /** Asked by the keyboard walk: the desk is no stop, and nothing in it is — it moves between its panes its own way. */
    inWalk() { return false; }
    /**
     * Told by the steward that the focus is inside the desk, and where (RFC 0066 E3, keyboard §17.5): the float
     * holding it comes to the front — any float on the desk's layer, the desk's own or one the page opened there.
     */
    within(on, at) {
        if (!on || !at || at.state === "away" || !at.root || !this._layer) return;
        var layer = this._layer, ids = layer.panes();
        for (var i = 0; i < ids.length; i++) {
            var p = layer.pane(ids[i]);
            if (p && p.root.contains(at.root)) { layer.raise(ids[i]); return; }
        }
    }

    /** The register's count changed: every dock's plus says whether a new tab would fit. */
    _roomed() {
        var room = this.register.room() > 0;
        for (var i = 0; i < this._docks.length; i++) if (typeof this._docks[i].setRoom === "function") this._docks[i].setRoom(room);
    }

    /** A dock's focus branch back where it was before the desk took it — or the root, if that is gone. */
    _home(pane) {
        var home = this._homes.get(pane), m = pane.focus ? pane.focus.owner : null;
        this._homes.delete(pane);
        if (!home || !m || m.in !== this.focus) return;
        var alive = home === focusParty.root || (home.owner && home.owner.in);
        (alive ? home : focusParty.root).adopt(m);
    }

    open(spec, host, index, how) {
        var mode = how == null ? "quiet" : String(how);
        if (_HOW.indexOf(mode) < 0) throw new Error("[Desk] '" + mode + "' is not how a tab arrives: " + _HOW.join(", "));
        if (!host) throw new Error("[Desk] open wants the host the tab-pane goes into");
        var tp = this.register.open(spec);
        try { this.move(tp, host, index); }
        catch (e) { tp.close(); throw e; }   // refused where it was going: nothing left behind
        if (mode !== "quiet") host.switchTab(tp.id);
        if (mode === "focus" && typeof tp.widget.activate === "function") tp.widget.activate();
        return tp;
    }

    move(tp, to, index) {
        var from = tp.host();
        if (!to || to === from) throw new Error("[Desk] a move is to another host");
        if (!to.admits(tp)) throw new Error("[Desk] slot '" + to.slotId + "' would not take tab-pane '" + tp.id + "'");
        var self = this, src = from ? from.tabIndexOf(tp.id) : -1, srcSlot = from ? from.slotId : null;
        function go() {
            if (from) from.letGo(tp);
            var at = to.take(tp, index == null ? null : index, true);   // placed, not yet shown: the arrival is said first
            self._fire(srcSlot === null ? PaneEvents.TabAdded(to.slotId, tp, at) : PaneEvents.TabMoved(srcSlot, tp, src, to.slotId, at));
            to.settle(tp.id);
            return at;
        }
        var fl = from ? this._floaters.get(from) : null;   // a float it leaves empty goes AFTER the move is said
        return fl ? fl.hold(go) : go();
    }

    unmount(tp) {
        var from = tp ? tp.host() : null;
        if (!from) return null;
        from.letGo(tp);   // not closing, so the host says nothing; a float left empty goes
        return from;
    }

    show(tp) {
        var host = tp ? tp.host() : null;
        if (!host) return null;
        host.switchTab(tp.id);
        var f = this._floaters.get(host);
        if (f && this._layer) this._layer.raise(f.id);
        return host;
    }

    detach(tp, at) {
        var r = Desk._rect(this.layer.root), p = Desk._placed(at || Desk._underChip(tp), r);
        var f = this.float({ x: p.x, y: p.y, w: _FLOAT_W, h: _FLOAT_H });
        try { this.move(tp, f.host); }
        catch (e) { f.close(); throw e; }
        return f;
    }

    float(opts) {
        var self = this, f = null, id = opts && opts.id != null ? String(opts.id) : this._floatId();
        f = new Floater(this.layer, Object.assign({
            id: id,
            menus: this._menus,
            onEvent: function (ev) { self._fire(ev); },
            onDragMove: function (frame, x, y) { if (f.host.count()) self._offer(x, y); else self._clear(); },
            onDragEnd: function (frame, x, y, ok) {
                var target = self._target, index = self._index;
                self._clear();
                if (!ok || !target || !f.host.count()) return;
                var tp = f.tabPanes()[0];
                self.move(tp, target, index);
                target.switchTab(tp.id);
            },
            onGone: function () { self.removeDock(f.host); self._floaters.delete(f.host); }
        }, opts || {}));
        this.addDock(f.host);
        this._floaters.set(f.host, f);
        return f;
    }

    /** A float's name no float on the desk has: float-1, float-2 and on. */
    _floatId() {
        var id;
        do { id = "float-" + (++this._floats); } while (this._layer && this._layer.has(id));
        return id;
    }

    /** The floats, bottom of the stack first. */
    floats() {
        if (!this._layer) return [];
        var byId = new Map();
        this._floaters.forEach(function (f) { byId.set(f.id, f); });
        return this._layer.panes().map(function (id) { return byId.get(id); }).filter(function (f) { return !!f; });
    }

    dispose() {
        if (this._disposed) return;
        this._disposed = true;
        this.register.dispose();   // every tab-pane closed: the docks empty, the floats folded
        if (this._layer) this._layer.dispose();
        this._docks.forEach(function (pane) { this._home(pane); }, this);   // the docks are the page's: back where they were
        this._docks = [];
        if (this.rest.owner.in) this.rest.owner.leave();
        if (this._ownFocus && this.focus.owner && this.focus.owner.in) this.focus.owner.leave();
        try { this.branch.dissolve(); } catch (e) {}
    }

    _fire(ev) {
        if (!this._sink) return;
        try { this._sink(ev); }
        catch (e) { console.error("[Desk] onEvent threw on " + ev.kind + ":", e); }
    }

    // ── the offer under the hand: a float's tab, over the docks it passes — never a float, which is no landing ──
    _offer(x, y) {
        var target = null, index = -1;
        for (var i = 0; i < this._docks.length; i++) {
            var d = this._docks[i];
            if (this._floaters.has(d)) continue;
            var at = target ? -1 : d.dropAt(x, y);
            if (at >= 0) { target = d; index = at; }
            else d.dropClear();
        }
        this._target = target;
        this._index = index;
    }
    _clear() {
        for (var i = 0; i < this._docks.length; i++) if (!this._floaters.has(this._docks[i])) this._docks[i].dropClear();
        this._target = null;
        this._index = -1;
    }

    /** Where a float with no hand goes: its bar at the point, the grip's offset in, kept within the desk. */
    static _placed(at, r) {
        var x = Math.max(0, (at && at.x != null ? at.x : r.left) - r.left - _GRIP_X);
        var y = Math.max(0, (at && at.y != null ? at.y : r.top) - r.top - _GRIP_Y);
        if (r.width) x = Math.min(x, Math.max(0, r.width - _FLOAT_W));
        if (r.height) y = Math.min(y, Math.max(0, r.height - _FLOAT_H));
        return { x: x, y: y };
    }

    /** The point a float's bar sits under when a tab is detached where it is: the float's corner at its chip's. */
    static _underChip(tp) {
        var c = Desk._rect(tp && tp.chip);
        return { x: c.left + _GRIP_X, y: (c.bottom != null ? c.bottom : c.top + (c.height || 0)) + _GRIP_Y };
    }

    static _rect(el) {
        return el && typeof el.getBoundingClientRect === "function" ? el.getBoundingClientRect() : { left: 0, top: 0, width: 0, height: 0 };
    }
}
