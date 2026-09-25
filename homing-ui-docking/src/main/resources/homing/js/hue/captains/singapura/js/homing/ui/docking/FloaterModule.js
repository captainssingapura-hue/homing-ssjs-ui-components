// =============================================================================
// Floater — a float: a frame on the desk around a host of its own, as a
// browser's window is a frame around its tabs (RFC 0066 E3, appendix
// "tab-panes", §7). It has ONE BAR, and the bar is its host's strip. The
// strip's own ground is the handle that moves the frame, with every tab-pane
// in it; the cross at the bar's end closes the float and every tab-pane in it;
// the frame's grip sizes it. There is no head and no title over the strip:
// the tabs are what it shows.
//
//   new Floater(desk, { id?, x?, y?, w?, h?, budget?, addable?, focus?, menus?, keys?, onEvent?, onDragMove?, onDragEnd?, onGone? })
//     id       its name on the desk, and its host's slot: the desk's own "pane-N" unless said
//     desk     the floating layer it lies on. The frame is the desk's, opened
//              with no head, never offered to a dock as one tab would be, and
//              not closed by the desk's Escape, since closing a float closes
//              its tabs. Its id is the desk's own.
//     budget, addable, focus, menus, keys   its host's, as a multi-tab pane takes them:
//              a float has no plus unless addable is true: it holds what came to it
//     onEvent  its host's reports: TabActivated, a reorder, a close
//     onDragMove(frame, x, y), onDragEnd(frame, x, y, ok)   its holder's, while the
//              frame is dragged: without them it is never offered to anything
//     onGone(floater)   it has closed, however it came to
//
//   floater.id  floater.frame  floater.host
//   floater.take(tp, index?)  → the index: the host takes the tab-pane in
//   floater.close()           every tab-pane in it closed, then the frame
//   floater.closed()
//   floater.tabPanes()        → the tab-panes in it, in order
//   floater.hold(fn)          → what fn returns: fn run with the float kept open though
//                             it empties, and folded after if it is still empty — so a
//                             move out of it is said before the float is gone
//
// It closes by itself when its last tab-pane leaves it, closed or moved away:
// an empty float is nothing, as an empty browser window is closed. The
// frame's name, for a reader, is the name of the tab it shows.
// =============================================================================

const _floaterOwner = Object.freeze({ toString: () => "floater" });

class Floater {
    constructor(desk, opts) {
        if (!desk) throw new Error("[Floater] the desk it lies on is required");
        var o = opts || {}, self = this;
        this.desk = desk;
        this._closed = false;
        this._holding = false;
        this._onGone = typeof o.onGone === "function" ? o.onGone : null;
        this.frame = desk.open({ id: o.id, head: false, closable: false, title: "", x: o.x, y: o.y, w: o.w, h: o.h,
                                 offered: typeof o.onDragMove === "function", onDragMove: o.onDragMove, onDragEnd: o.onDragEnd });
        this.id = this.frame.id;
        var own = this.frame.branch.createBranch("floater");
        own.activate(_floaterOwner);
        this.host = new MultiTabPane(own.createBranch("host"), {
            host: this.frame.body, slotId: this.id, budget: o.budget, addable: o.addable === true, focus: o.focus, menus: o.menus, keys: o.keys,
            focusName: this.frame.branch.name,   // the frame's own name, unique on the page: every float's host is on a branch called "host", and a focus branch refuses a name twice
            onEvent: function (ev) {
                if (ev.kind === "TabActivated") self._named(ev.tabId);
                if (typeof o.onEvent === "function") o.onEvent(ev);
            },
            onEmpty: function () { if (!self._holding) self._fold(); },
            onClose: function () { self.close(); }
        });
        this.frame.handle(this.host.bar(), function (ev) { return self.host.barGround(ev.target); });
    }

    take(tp, index) { return this.host.take(tp, index); }

    close() {
        var self = this;
        if (this._closed) return this;
        this.host.tabs().forEach(function (id) { if (self.host.has(id)) self.host.removeTab(id); });
        this._fold();   // the last close folded it already; an empty one folds here
        return this;
    }

    closed() { return this._closed; }

    hold(fn) {
        this._holding = true;
        try { return fn(); }
        finally { this._holding = false; if (!this._closed && this.host.count() === 0) this._fold(); }
    }

    tabPanes() {
        var self = this;
        return this.host.tabs().map(function (id) { return self.host.tabPaneOf(id); }).filter(Boolean);
    }

    /** Gone: the host first, which holds nothing now, then the frame, whose branch the host's was under. */
    _fold() {
        if (this._closed) return;
        this._closed = true;
        this.host.dispose();
        this.desk.close(this.id);
        if (this._onGone) this._onGone(this);
    }

    /** The frame's name for a reader: the tab it shows. */
    _named(tabId) {
        var tabs = this.host.getState().tabs;
        for (var i = 0; i < tabs.length; i++) if (tabs[i].id === tabId) { this.frame.title(tabs[i].title == null ? "" : tabs[i].title); return; }
    }
}
