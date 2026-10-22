// =============================================================================
// FloatingPaneBehavior — what a floating pane does: the hand coming over it and
// leaving it, told to the pane; and while its head is held, each move of the
// hand told to the pane - how far it has moved since it went down - until it is
// let go. Where it goes, and whether it is in the hand, are the pane's. It
// never touches the look.
//
//   new FloatingPaneBehavior(el, pane)   behavior.grab()   behavior.drop()
// =============================================================================

class FloatingPaneBehavior {
    constructor(el, pane) {
        var self = this;
        this._doc = el.ownerDocument;
        this._from = null;
        this._grabbed = false;
        el.addEventListener("mouseenter", function () { pane._hovered(true); });
        el.addEventListener("mouseleave", function () { pane._hovered(false); });
        el.addEventListener("mousedown", function (ev) { self._from = { x: ev.clientX, y: ev.clientY }; }, true);   // where the hand went down: before its head hears it
        this._move = function (ev) { if (self._from !== null) pane._moved(ev.clientX - self._from.x, ev.clientY - self._from.y); };
    }

    /** Its head held: the hand's moves heard. */
    grab() {
        if (this._grabbed) return;
        this._grabbed = true;
        this._doc.addEventListener("mousemove", this._move);
    }

    /** Let go: heard no more. */
    drop() {
        if (!this._grabbed) return;
        this._grabbed = false;
        this._from = null;
        this._doc.removeEventListener("mousemove", this._move);
    }
}
