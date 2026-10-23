// =============================================================================
// MultiTabPaneFloats — a multi-tab pane's floaters, the pane's own: a tab torn
// off its bar carried under the hand in a floater of its own, taken back onto
// the bar within reach of it, or left afloat; a floater dragged by its head
// docked again on the bar; and a tab of another pane's caught on its bar, the
// hand still on it (owner, 23 Oct) - told to the pane's caller, who moves the
// tab over. Its floaters are drawn on the pane's desk, where it was made with
// one, else in the pane itself. Split from the pane, its line limit reached
// (23 Oct); made by the pane, once - nothing outside it holds one.
//
//   new MultiTabPaneFloats(pane)   floats.tear(id)   floats.handAt(x, y)   floats.handUp()
//   floats.inHand(id, on)   floats.forget(id)   floats.land(t)   floats.expect(id)   floats.drop()
// =============================================================================

class MultiTabPaneFloats {
    /** How near the bar's middle a chip comes to be taken onto it, in pixels. */
    static CAPTURE = 8;
    /** Where the hand holds a floater it carries: on its head's chip, from its corner, in pixels. */
    static HOLD = Object.freeze({ x: 96, y: 19 });
    /** How much of the slot a floater takes, each way, where its tab says no size of its own (owner, 23 Oct). */
    static SHARE = 0.6;

    constructor(pane) {
        var self = this;
        this._pane = pane;
        this._doc = pane.el.ownerDocument;
        this.carrying = null;   // the tab the hand carries off its bar, in a floater under it
        this._offset = 0;   // how far below its chip's middle the hand took the tab carried
        this.dragged = null;   // the tab whose floater is in the hand, moved by its head
        this._top = 0;   // the top of its floaters' stack
        this._expected = null;   // a tab of another pane's in the hand, as its caller says: caught should its chip come onto the bar
        this._expectMove = function (ev) { self._expecting(ev.clientX, ev.clientY); };
        this._expectUp = function () { self.expect(null); };
    }

    /** Where its floaters are drawn: its pane's desk, or the pane itself. */
    get holder() { return this._pane.fixed.desk ?? this._pane.el; }

    /**
     * A tab torn off the bar: carried in a floater of its own, made under the hand - its head the tab's chip, its size the
     * tab's own or a share of the slot, on top - what it shows moved into it, never rebuilt; in transit.
     */
    tear(id) {
        var pane = this._pane, t = pane._tabs.get(id), slot = pane._slot.getBoundingClientRect(), bar = pane._strip.el.getBoundingClientRect(), press = pane._behavior.press;
        var f = pane._floater(id), share = MultiTabPaneFloats.SHARE;
        this.holder.appendChild(f.root);
        pane._behavior.hear(f.root);
        f.size(t.size !== null ? t.size.w : Math.round(slot.width * share), t.size !== null ? t.size.h : Math.round(slot.height * share));
        f.z = ++this._top;
        if (t.root !== null) { t.pane.removeChild(t.root); f.mount(t.root); }
        t.floater = f;
        this.carrying = id;
        this._offset = press !== null ? press.y - (bar.top + bar.height / 2) : 0;
        pane._transit(id);
        if (pane._behavior.at !== null) this._follow(f, pane._behavior.at.x, pane._behavior.at.y);
    }

    /** A floater put under the hand: the hand on its head's chip, wherever it is drawn. */
    _follow(f, x, y) {
        var holder = f.root.offsetParent || this.holder, r = holder.getBoundingClientRect(), hold = MultiTabPaneFloats.HOLD;
        f.at(x - r.left - holder.clientLeft - hold.x, y - r.top - holder.clientTop - hold.y);
    }

    /** Whether the hand - its chip's middle - is on the bar: within CAPTURE of its middle, along it. */
    onBar(x, y, offset) {
        var b = this._pane._strip.el.getBoundingClientRect(), mid = b.top + b.height / 2;
        return Math.abs(y - offset - mid) <= MultiTabPaneFloats.CAPTURE && x >= b.left && x <= b.right;
    }

    /** The hand moved, down on the pane or one of its floaters: the tab carried follows it, and is taken back on the bar; a floater in the hand docks on it. */
    handAt(x, y) {
        if (this.carrying !== null) {
            this._follow(this._pane._tabs.get(this.carrying).floater, x, y);
            if (this.onBar(x, y, this._offset)) this._back(this.carrying, x, y);
            return;
        }
        if (this.dragged !== null && this.onBar(x, y, 0)) this._back(this.dragged, x, y);
    }

    /** The hand let go: the tab carried stays afloat - detached, in transit no more. */
    handUp() {
        var id = this.carrying;
        if (id === null) return;
        this.carrying = null;
        this._pane._afloat(id);
        this._pane._transit(null);
    }

    /** A tab carried, or a floater in the hand, back on the bar under the hand: its floater gone, its chip there, sliding on. */
    _back(id, x, y) {
        this.forget(id);
        this.land(this._pane._tabs.get(id));
        this._pane._strip.capture(id, x, y);
    }

    /** A tab's floater dropped: what it shows back in the tab's pane. */
    land(t) {
        var f = t.floater;
        t.floater = null;
        if (t.root !== null) { f.unmount(); t.pane.appendChild(t.root); }
        f.dispose();
    }

    /** One of its floaters in the hand, or let go: on top while it is moved, the one in the hand kept - in transit, or no more. */
    inHand(id, on) {
        var t = this._pane._tabs.get(id);
        if (on) { this.dragged = id; if (t && t.floater !== null) t.floater.z = ++this._top; this._pane._transit(id); }
        else if (this.dragged === id) { this.dragged = null; this._pane._transit(null); }
    }

    /** A tab no longer in the hand - docked, or taken out: carried and dragged no more, in transit no more. */
    forget(id) {
        if (this.carrying === id) this.carrying = null;
        if (this.dragged === id) this.dragged = null;
        if (this._pane.transit === id) this._pane._transit(null);
    }

    /**
     * A tab of another pane's in the hand, as its caller says - or none: the hand heard until it is let go, the tab caught
     * should its chip come onto the bar - unless the pane holds a tab of that id.
     */
    expect(id) {
        if (this._expected !== null) {
            this._doc.removeEventListener("mousemove", this._expectMove, true);
            this._doc.removeEventListener("mouseup", this._expectUp, true);
        }
        this._expected = id ?? null;
        if (this._expected === null) return;
        this._doc.addEventListener("mousemove", this._expectMove, true);
        this._doc.addEventListener("mouseup", this._expectUp, true);
    }

    /** The hand moved, a tab of another's expected: on the bar, caught - told, its caller moving it over - its chip then under the hand, sliding on. */
    _expecting(x, y) {
        var id = this._expected, pane = this._pane;
        if (id === null || pane._tabs.has(id) || !this.onBar(x, y, 0)) return;
        this.expect(null);
        pane._caught(id);
        if (pane._tabs.has(id)) pane._strip.capture(id, x, y);
    }

    /** It goes with its pane: heard no more. */
    drop() { this.expect(null); }
}
