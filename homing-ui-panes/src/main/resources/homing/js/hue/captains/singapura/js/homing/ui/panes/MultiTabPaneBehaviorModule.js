// =============================================================================
// MultiTabPaneBehavior — what a multi-tab pane does: while the hand is down on
// it - on its bar, or on one of its floaters - where it went down and where it
// is now are kept, each move and the hand let go told to the pane, before any
// of its parts hears them: what the pane makes of them - a tab carried off its
// bar, a floater brought back onto it - is its own. It never touches the look.
//
//   new MultiTabPaneBehavior(el, pane)   behavior.press   behavior.at
// =============================================================================

class MultiTabPaneBehavior {
    constructor(el, pane) {
        var self = this, doc = el.ownerDocument;
        this._doc = doc;
        this.press = null;   // where the hand went down on it, while it is down
        this.at = null;      // where the hand is, while it is down
        this._move = function (ev) { self.at = { x: ev.clientX, y: ev.clientY }; pane._handAt(ev.clientX, ev.clientY); };
        this._up = function (ev) {
            if (ev.button !== 0) return;
            self._stop();
            pane._handUp(ev.clientX, ev.clientY);
        };
        el.addEventListener("mousedown", function (ev) {
            if (ev.button !== 0) return;
            self.press = self.at = { x: ev.clientX, y: ev.clientY };
            doc.addEventListener("mousemove", self._move, true);   // before its parts hear it: theirs come after
            doc.addEventListener("mouseup", self._up, true);
        }, true);
    }

    /** The hand let go: heard no more until it goes down again. */
    _stop() {
        this._doc.removeEventListener("mousemove", this._move, true);
        this._doc.removeEventListener("mouseup", this._up, true);
        this.press = null;
    }

    /** It and its listeners go: heard no more. */
    drop() { this._stop(); }
}
