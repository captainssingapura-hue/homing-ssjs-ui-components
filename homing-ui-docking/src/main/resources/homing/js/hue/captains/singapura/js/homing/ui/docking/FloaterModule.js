// =============================================================================
// Floater — a float: a frame on the desk around ONE tab in transit (RFC 0066
// E3, appendix "tab-panes", §7). A float is the desk's temporary vehicle and
// never a second dock, so its host is a SingleTabPane and takes one tab and
// no more: it is never offered a tab, by a drag or a move. It has ONE BAR,
// and the bar is the tab's chip: the whole bar, the chip with it, is the
// handle that moves the frame; the chip's cross closes the tab, and the float
// with it; the frame's grip sizes it. There is no head and no title over it.
//
//   new Floater(desk, { id?, x?, y?, w?, h?, focus?, menus?, onEvent?, onDragMove?, onDragEnd?, onGone? })
//     id       its name on the desk, and its host's slot: the desk's own "pane-N" unless said
//     desk     the floating layer it lies on. The frame is the desk's, opened
//              with no head, and not closed by the desk's Escape, since closing
//              a float closes its tab. Its id is the desk's own.
//     focus, menus   its host's, as a single-tab pane takes them
//     onEvent  its host's reports: TabActivated, TabRenamed, a close
//     onDragMove(frame, x, y), onDragEnd(frame, x, y, ok)   its holder's, while the
//              frame is dragged: without them it is never offered to anything
//     onGone(floater)   it has closed, however it came to
//
//   floater.id  floater.frame  floater.host
//   floater.take(tp)          → the index: the host takes the tab-pane in, when it carries none
//   floater.close()           its tab-pane asked to close - closed, when no owner takes the
//                             asking (TabPane.requestClose) - then the frame, once it is empty
//   floater.closed()
//   floater.tabPanes()        → the tab-pane in it, as a list of one, or none
//   floater.hold(fn)          → what fn returns: fn run with the float kept open though
//                             it empties, and folded after if it is still empty — so a
//                             move out of it is said before the float is gone
//
// It closes by itself when its tab-pane leaves it, closed or moved away: an
// empty float is nothing. The frame's name, for a reader, is its tab's.
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
        this.host = new SingleTabPane(own.createBranch("host"), {
            host: this.frame.body, slotId: this.id, focus: o.focus, menus: o.menus,
            focusName: this.frame.branch.name,   // the frame's own name, unique on the page: every float's host is on a branch called "host", and a focus branch refuses a name twice
            onEvent: function (ev) {
                if (ev.kind === "TabActivated" || ev.kind === "TabRenamed") self._named(ev.tabId);
                if (typeof o.onEvent === "function") o.onEvent(ev);
            },
            onEmpty: function () { if (!self._holding) self._fold(); }
        });
        this.frame.handle(this.host.bar(), function (ev) { return self.host.barGround(ev.target); });
    }

    take(tp) { return this.host.take(tp); }

    close() {
        var self = this;
        if (this._closed) return this;
        // each ASKED to close, as its cross asks: a tab-pane whose owner closes it in its own order is left to it
        this.tabPanes().forEach(function (tp) { if (self.host.has(tp.id) && !tp.requestClose()) self.host.removeTab(tp.id); });
        if (this.host.count() === 0) this._fold();   // the last close folded it already; an empty one folds here; one its owners kept stays
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
