// =============================================================================
// TabStripBehavior — what a bar of tabs does: where the hand goes down on it is
// kept, before any chip hears it; and while one of its chips is held, each move
// of the hand is told to the bar - where the hand is along it, and how far it
// has moved since it went down, along and across - and the hand let go,
// wherever it is by then. Which chip is held, and what a move does, are the
// bar's. It never touches the look.
//
//   new TabStripBehavior(el, strip)   behavior.grab()   behavior.drop()   behavior.from(x, y)
// =============================================================================

class TabStripBehavior {
    constructor(el, strip) {
        var self = this;
        this._doc = el.ownerDocument;
        this._from = { x: 0, y: 0 };
        this._grabbed = false;
        el.addEventListener("mousedown", function (ev) { self._from = { x: ev.clientX, y: ev.clientY }; }, true);
        this._move = function (ev) { strip._dragTo(ev.clientX, ev.clientX - self._from.x, ev.clientY - self._from.y); };
        this._up = function (ev) { if (ev.button === 0) strip._letGoAt(); };
    }

    /** A chip held: the hand's moves heard, and its letting go. */
    grab() {
        if (this._grabbed) return;
        this._grabbed = true;
        this._doc.addEventListener("mousemove", this._move);
        this._doc.addEventListener("mouseup", this._up);
    }

    /** Let go: heard no more. */
    drop() {
        if (!this._grabbed) return;
        this._grabbed = false;
        this._doc.removeEventListener("mousemove", this._move);
        this._doc.removeEventListener("mouseup", this._up);
    }

    /** Where the hand is taken to have gone down: a chip taken back on the bar under it, the hand still down. */
    from(x, y) { this._from = { x: x, y: y }; }
}
