// =============================================================================
// MultiTabPaneBehavior — what a multi-tab pane does: while the hand is down on
// it - on its bar, or on one of its floaters - where it went down and where it
// is now are kept, each move and the hand let go told to the pane, before any
// of its parts hears them: what the pane makes of them - a tab carried off its
// bar, a floater brought back onto it - is its own. A right-press on it is told
// to the pane's menu, where it has one: the browser's own kept away only where
// the menu opened. Its pane's floaters, drawn on its desk, are heard as it is.
// It never touches the look.
//
//   new MultiTabPaneBehavior(el, pane)   behavior.press   behavior.at   behavior.hear(floater)
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
        el.addEventListener("contextmenu", function (ev) {
            if (pane._menu !== null && pane._menu.ask(ev.target, ev.clientX, ev.clientY)) ev.preventDefault();
        });
        this._down = function (ev) {
            if (ev.button !== 0) return;
            self.press = self.at = { x: ev.clientX, y: ev.clientY };
            doc.addEventListener("mousemove", self._move, true);   // before its parts hear it: theirs come after
            doc.addEventListener("mouseup", self._up, true);
        };
        el.addEventListener("mousedown", this._down, true);
    }

    /** One of its pane's floaters, drawn away from it - on its desk: the hand going down on it heard as on the pane. */
    hear(el) { el.addEventListener("mousedown", this._down, true); }

    /** The hand let go: heard no more until it goes down again. */
    _stop() {
        this._doc.removeEventListener("mousemove", this._move, true);
        this._doc.removeEventListener("mouseup", this._up, true);
        this.press = null;
    }

    /** It and its listeners go: heard no more. */
    drop() { this._stop(); }
}
