// =============================================================================
// Desk — the whole (RFC 0066 E3, appendix "tab-panes", §3): the register of a
// desk's tab-panes, the hosts that hold them — DOCKS, the multi-tab panes the
// page places where it likes, and FLOATS, which are the desk's own — the float
// layer the floats lie on, and every move of a tab-pane between them. A tab-
// pane is opened in the desk's register and owned there for its whole life; a
// host holds it and never owns it. A lone multi-tab pane is a desk with one
// host: the float layer is made only when a float is first wanted.
//
//   new Desk(branch, { host, onEvent?, focus?, focusName?, menus?, keyboard?, keyboardId?, minW?, minH? })
//     branch   the desk's own, handed unactivated
//     host     the positioned box its docks sit in; the float layer lies over
//              it, the hand passing through except on a frame
//     focus    the desk's focus branch, where a tab-pane's widget RESTS while no
//              host holds it; one of its own under the page's root unless said,
//              named focusName, the branch's name unless said
//     menus    the page's ContextMenuSteward, handed to its floats, so a chip
//              afloat asks for its menu as a chip in a dock does
//     onEvent  its reports: a tab-pane's arrival (TabAdded) and every move
//              between hosts (TabMoved), a float's host's reports, the layer's
//
//   desk.register          the TabRegister: every tab-pane of this desk is opened there
//   desk.layer             the FloatLayer, made when first wanted
//   desk.addDock(pane) .removeDock(pane) .docks()
//                          a multi-tab pane becomes a host of the desk: a tab-pane dragged over
//                          its strip is offered to it. docks() are the docks and the floats
//   desk.open({ id?, title?, icon?, pinned?, closable?, make }, host, index?, how?) → the TabPane:
//                          opened in the register — its widget made by make(branch, tab) — and
//                          put in the host, which says it arrived (TabAdded). how: "quiet", the
//                          host's own rule on what shows; "front", shown; "focus", shown and its
//                          widget handed the keys. "quiet" unless said
//   desk.move(tp, to, index?) → the index it took: from the host it is in to another, whole or
//                          not at all — refused before anything leaves when that host would not
//                          take it. One TabMoved; a tab-pane in no host arrives, one TabAdded
//   desk.detach(tp, at?)   → the float: the tab-pane into a float of its own, its bar at the
//                          point, within the desk — a menu's Detach, Shift+↓
//   desk.float(opts?)      → a Floater: a frame around a host of its own, one bar, no plus.
//                          While it holds ONE tab-pane it is that tab in the hand: dragged, it
//                          is offered to the docks it passes, and let go over a strip the tab-pane
//                          lands there, the one the dock shows - the hand put it there to look at
//                          it - and the float, empty, is gone. A float of many moves as a
//                          window does. A float is a dock for as long as it lasts
//   desk.dispose()         every tab-pane closed, the floats with them; the layer; the desk's
//                          own focus branch left. The docks are the page's
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
        this.register = new TabRegister(branch.createBranch("tabs"), { focus: this.focus });
        this._layer = null;
        this._docks = [];
        this._floaters = new Map();   // a float's host → the float, while it lasts
        this._floats = 0;             // float-1, float-2 and on: never a name another float of this desk had
        this._target = null;
        this._index = -1;
        this._disposed = false;
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

    addDock(pane) { if (this._docks.indexOf(pane) < 0) this._docks.push(pane); return this; }
    removeDock(pane) { var i = this._docks.indexOf(pane); if (i >= 0) this._docks.splice(i, 1); return this; }
    docks() { return this._docks.slice(); }

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

    detach(tp, at) {
        var r = Desk._rect(this.layer.root), p = Desk._placed(at, r);
        var f = this.float({ x: p.x, y: p.y, w: _FLOAT_W, h: _FLOAT_H });
        try { this.move(tp, f.host); }
        catch (e) { f.close(); throw e; }
        return f;
    }

    float(opts) {
        var self = this, f = null;
        f = new Floater(this.layer, Object.assign({
            id: "float-" + (++this._floats),
            menus: this._menus,
            onEvent: function (ev) { self._fire(ev); },
            onDragMove: function (frame, x, y) { if (f.host.count() === 1) self._offer(x, y, f.host); else self._clear(); },
            onDragEnd: function (frame, x, y, ok) {
                var target = self._target, index = self._index;
                self._clear();
                if (!ok || !target || f.host.count() !== 1) return;
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

    dispose() {
        if (this._disposed) return;
        this._disposed = true;
        this.register.dispose();   // every tab-pane closed: the docks empty, the floats folded
        if (this._layer) this._layer.dispose();
        this._docks = [];
        if (this._ownFocus && this.focus.owner && this.focus.owner.in) this.focus.owner.leave();
        try { this.branch.dissolve(); } catch (e) {}
    }

    _fire(ev) {
        if (!this._sink) return;
        try { this._sink(ev); }
        catch (e) { console.error("[Desk] onEvent threw on " + ev.kind + ":", e); }
    }

    // ── the offer under the hand: a float of one, over the docks it passes ──
    _offer(x, y, except) {
        var target = null, index = -1;
        for (var i = 0; i < this._docks.length; i++) {
            var at = target || this._docks[i] === except ? -1 : this._docks[i].dropAt(x, y);
            if (at >= 0) { target = this._docks[i]; index = at; }
            else this._docks[i].dropClear();
        }
        this._target = target;
        this._index = index;
    }
    _clear() {
        for (var i = 0; i < this._docks.length; i++) this._docks[i].dropClear();
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

    static _rect(el) {
        return el && typeof el.getBoundingClientRect === "function" ? el.getBoundingClientRect() : { left: 0, top: 0, width: 0, height: 0 };
    }
}
