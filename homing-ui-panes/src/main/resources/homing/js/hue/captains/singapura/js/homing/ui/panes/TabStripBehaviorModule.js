// =============================================================================
// TabStripBehavior — what a bar of tabs does: where the hand goes down on it is
// kept, before any chip hears it; and while one of its chips is held, each move
// of the hand is told to the bar - where the hand is along it, and how far it
// has moved since it went down - until the chip is let go. Which chip is held,
// and what a move does, are the bar's. It never touches the look.
//
//   new TabStripBehavior(el, strip)   behavior.grab()   behavior.drop()
// =============================================================================

class TabStripBehavior {
    constructor(el, strip) {
        var self = this;
        this._doc = el.ownerDocument;
        this._from = 0;
        this._grabbed = false;
        el.addEventListener("mousedown", function (ev) { self._from = ev.clientX; }, true);
        this._move = function (ev) { strip._dragTo(ev.clientX, ev.clientX - self._from); };
    }

    /** A chip held: the hand's moves heard. */
    grab() {
        if (this._grabbed) return;
        this._grabbed = true;
        this._doc.addEventListener("mousemove", this._move);
    }

    /** Let go: heard no more. */
    drop() {
        if (!this._grabbed) return;
        this._grabbed = false;
        this._doc.removeEventListener("mousemove", this._move);
    }
}
