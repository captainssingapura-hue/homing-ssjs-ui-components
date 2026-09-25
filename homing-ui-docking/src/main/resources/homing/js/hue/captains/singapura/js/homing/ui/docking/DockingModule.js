// =============================================================================
// Docking — the protocol between a desk and its docks. A branch component: the
// caller makes a sub-branch for it and hands it in; it owns the desk, as a
// layer over the docks' host, and a dock is a multi-tab pane given to it.
//
//   new Docking(branch, { host, onEvent?, minW?, minH?, keyboard?, keyboardId?, menus? })
//     menus  the page's ContextMenuSteward, handed to every float it opens, so a chip afloat
//               asks for its menu as a chip in a dock does
//     keyboard  the page's KeyboardSteward, handed on to the desk, which joins as
//               keyboardId (the desk's branch's name, unless said)
//     host   a positioned box holding the docks; the desk lies over it, the
//            hand passing through except on a floating pane. A pane floats
//            only within it.
//
//   docking.desk                 the desk; open a floating tab on it as usual
//   docking.addDock(pane)        a multi-tab pane becomes a dock
//   docking.removeDock(pane)
//   docking.undock(dock, tab, e, grab?)
//                                by call, with a pointer event: the tab leaves the
//                                dock, NOT disposed, and floats under the hand —
//                                the pane opens where the pointer is, its head
//                                under the hand at the grab, an offset within the
//                                chip, when given — and takes the drag over. The
//                                strip does not pull a tab off yet; a holder may.
//                                Undocked(tabId, slotId), after the desk's Opened
//   docking.undockAt(dock, tab, at)
//                                by call, with no hand — a menu's pick: the tab
//                                leaves the dock, NOT disposed, and floats with
//                                its head at the point, within the desk; nothing
//                                is grabbed. Undocked(tabId, slotId), after Opened.
//                                Either way the float is whole or not at all: a
//                                tab already afloat under the id is refused before
//                                anything moves, and a tab the desk refuses anyway
//                                goes back to its place on the dock — TabAttached
//                                there, active again if it was — and the error is
//                                thrown on. Nothing is carried, nothing said.
//   docking.dock(paneId, dock, index?)
//                                the floating tab leaves the desk (Released) and
//                                is attached to the dock (TabAttached), then
//                                Docked(tabId, slotId, index). What is attached is
//                                the TAB that left the dock - the holder's own
//                                object, with whatever the holder wrote on it -
//                                carrying the name and icon the pane had last
//   docking.float(opts?)         → a Floater on the desk: a frame around a host of its own,
//                                its one bar the host's strip (RFC 0066 E3, appendix
//                                "tab-panes", §7); its host's reports on this sink unless said.
//                                A float is a dock too, for as long as it lasts: a tab-pane
//                                dragged over its strip is offered to it. While a float holds
//                                ONE tab-pane it is that tab in the hand: dragged, it is offered
//                                to the docks it passes, and let go over one's strip the tab-pane
//                                moves there, and the float, empty, is gone. A float of many
//                                moves as a window does, offered to nothing
//   docking.detach(tp, at?)      → the float: the tab-pane off the host it is in and into a new
//                                float, its bar at the point, within the desk — a menu's Detach
//   docking.move(tp, to, index?) → the index it took: the tab-pane from the host it is in to
//                                another, whole or not at all — refused before anything leaves
//                                when that host would not take it. One TabMoved, from here; a
//                                tab-pane in no host yet arrives, and that is one TabAdded
//   docking.dispose()            the desk and everything on it; the docks stay
//
// The hand: while a floating pane is dragged, every dock under the pointer is
// offered the tab — the dock wears the drop-target word and marks where the
// tab would land, over its strip at the mark, over its content at the end —
// and the one still under the pointer when the hand lets go takes it. The
// dock's events and the desk's come through the same sink as Docking's own.
// =============================================================================

const _dockingOwner = Object.freeze({ toString: () => "docking" });
var _GRIP_X = 60, _GRIP_Y = 14;      // where the head sits under the hand that pulled the tab
var _FLOAT_W = 320, _FLOAT_H = 220;

class Docking {
    constructor(branch, opts) {
        if (!branch) throw new Error("[Docking] a branch of its own is required");
        if (!opts || !opts.host) throw new Error("[Docking] opts.host is required");
        var self = this;
        branch.activate(_dockingOwner);
        this.branch = branch;
        this._sink = typeof opts.onEvent === "function" ? opts.onEvent : null;
        this._docks = [];
        this._carried = new Map();    // tabId → the tab that left a dock, while it floats
        this._floaters = new Map();   // a float's host → the float, while it lasts
        this._menus = opts.menus || null;
        this._floats = 0;             // its floats are float-1, float-2 and on: never a name another float of this desk had
        this._target = null;
        this._index = -1;
        this.desk = new Desk(branch.createBranch("desk"), {
            host: opts.host, layer: true, minW: opts.minW, minH: opts.minH, keyboard: opts.keyboard, keyboardId: opts.keyboardId,
            onEvent: function (ev) { self._fire(ev); },
            onDragMove: function (pane, x, y) { self._offer(x, y); },
            onDragEnd: function (pane, x, y, ok) { self._drop(pane, ok); }
        });
    }

    addDock(pane) { if (this._docks.indexOf(pane) < 0) this._docks.push(pane); return this; }
    removeDock(pane) { var i = this._docks.indexOf(pane); if (i >= 0) this._docks.splice(i, 1); return this; }
    docks() { return this._docks.slice(); }

    /** A chip pulled off a dock's strip: the tab floats under the same hand. */
    undock(dock, tab, e, grab) {
        var r = Docking._rect(this.desk.root);
        var gx = grab && grab.x >= 0 ? grab.x : _GRIP_X, gy = grab && grab.y >= 0 ? grab.y : _GRIP_Y;
        var pane = this._float(dock, tab, e.clientX - r.left - gx, e.clientY - r.top - gy);
        pane.grab(e.pointerId, e.clientX, e.clientY);
        return pane;
    }

    /** A tab detached by call with no hand — a menu's pick — floating with its head at the point, kept within the desk. */
    undockAt(dock, tab, at) {
        var p = Docking._placed(at, Docking._rect(this.desk.root));
        return this._float(dock, tab, p.x, p.y);
    }

    /** Where a float with no hand goes: its bar at the point, the grip's offset in, kept within the desk. */
    static _placed(at, r) {
        var x = Math.max(0, (at && at.x != null ? at.x : r.left) - r.left - _GRIP_X);
        var y = Math.max(0, (at && at.y != null ? at.y : r.top) - r.top - _GRIP_Y);
        if (r.width) x = Math.min(x, Math.max(0, r.width - _FLOAT_W));
        if (r.height) y = Math.min(y, Math.max(0, r.height - _FLOAT_H));
        return { x: x, y: y };
    }

    /**
     * The tab off its dock and onto the desk, or left where it was. The widget's
     * root can only go to the pane by leaving the dock, so the tab is detached
     * first; a desk that refuses it then has it put back, at its index.
     */
    _float(dock, tab, x, y) {
        if (this.desk.has(tab.id)) throw new Error("[Docking] a tab is already afloat as '" + tab.id + "'");
        var index = dock.tabIndexOf(tab.id), wasActive = dock.activeTab() === tab.id;
        var t = dock.detachTab(tab.id), pane;
        try {
            pane = this.desk.open({
                id: t.id, title: t.title == null ? t.id : t.title, icon: t.icon || null, widget: t.widget, closable: t.closable !== false,
                x: x, y: y, w: _FLOAT_W, h: _FLOAT_H
            });
        } catch (err) {
            dock.attachTab(t, index);
            if (wasActive) dock.switchTab(t.id);
            throw err;
        }
        this._carried.set(t.id, t);
        this._fire(DockEvents.Undocked(t.id, dock.slotId));
        return pane;
    }

    /** A floating tab into a dock, at an index or the end. */
    dock(paneId, dock, index) {
        var released = this.desk.release(paneId);
        if (!released) throw new Error("[Docking] no floating pane '" + paneId + "'");
        // The tab that left, not the desk's account of it: the holder's object,
        // with the name and icon the pane had when it came down.
        var tab = this._carried.get(released.id) || released;
        this._carried.delete(released.id);
        if (tab !== released) { tab.title = released.title; tab.icon = released.icon; tab.widget = released.widget; }
        var at = dock.attachTab(tab, index == null ? null : index);
        this._fire(DockEvents.Docked(tab.id, dock.slotId, at));
        return at;
    }

    float(opts) {
        var self = this, f = null;
        f = new Floater(this.desk, Object.assign({
            id: "float-" + (++this._floats),
            menus: this._menus,
            onEvent: function (ev) { self._fire(ev); },
            onDragMove: function (frame, x, y) { if (f.host.count() === 1) self._offer(x, y, f.host); else self._clear(); },
            onDragEnd: function (frame, x, y, ok) {
                var target = self._target, index = self._index;
                self._clear();
                if (ok && target && f.host.count() === 1) self.move(f.tabPanes()[0], target, index);
            },
            onGone: function () { self.removeDock(f.host); self._floaters.delete(f.host); }
        }, opts || {}));
        this.addDock(f.host);
        this._floaters.set(f.host, f);
        return f;
    }

    detach(tp, at) {
        var p = Docking._placed(at, Docking._rect(this.desk.root));
        var f = this.float({ x: p.x, y: p.y, w: _FLOAT_W, h: _FLOAT_H });
        try { this.move(tp, f.host); }
        catch (e) { f.close(); throw e; }
        return f;
    }

    move(tp, to, index) {
        var from = tp.host();
        if (!to || to === from) throw new Error("[Docking] a move is to another host");
        if (!to.admits(tp)) throw new Error("[Docking] slot '" + to.slotId + "' would not take tab-pane '" + tp.id + "'");
        var self = this, src = from ? from.tabIndexOf(tp.id) : -1, srcSlot = from ? from.slotId : null;
        function go() {
            if (from) from.letGo(tp);
            var at = to.take(tp, index == null ? null : index);
            self._fire(srcSlot === null ? PaneEvents.TabAdded(to.slotId, tp, at) : PaneEvents.TabMoved(srcSlot, tp, src, to.slotId, at));
            return at;
        }
        var fl = from ? this._floaters.get(from) : null;   // a float it leaves empty goes AFTER the move is said
        return fl ? fl.hold(go) : go();
    }

    dispose() {
        this.desk.dispose();
        this._docks = [];
        try { this.branch.dissolve(); } catch (e) {}
    }

    // ── the offer under the hand ────────────────────────────────────────────
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
    _drop(pane, ok) {
        var target = this._target, index = this._index;
        this._clear();
        if (ok && target) this.dock(pane.id, target, index);
    }

    static _rect(el) {
        return el && typeof el.getBoundingClientRect === "function" ? el.getBoundingClientRect() : { left: 0, top: 0, width: 0, height: 0 };
    }

    _fire(ev) {
        if (ev && ev.kind === "Closed") this._carried.delete(ev.id);   // closed afloat: it never comes back
        if (!this._sink) return;
        try { this._sink(ev); }
        catch (e) { console.error("[Docking] onEvent threw on " + ev.kind + ":", e); }
    }
}
