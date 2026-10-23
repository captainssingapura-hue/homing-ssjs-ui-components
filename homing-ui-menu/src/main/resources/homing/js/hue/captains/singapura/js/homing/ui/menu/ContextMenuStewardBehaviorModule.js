// =============================================================================
// ContextMenuStewardBehavior — what the menus' steward does with the page while
// a menu is open, and only then. A press anywhere, heard in the capture before
// anything under the pointer: outside the menu, the steward closes it, and the
// press and the rest of its gesture - its release, its click - are swallowed, so
// nothing under the pointer is acted on; the contextmenu that follows a
// right-press is let through, so the next menu opens in the same gesture. The
// gesture is over once its click is swallowed, or once its release is done. A
// scroll of the page or of any box, a resize, the window losing the focus: each
// told to the steward, whose to say what closes it. It keeps no state but its
// listeners, and never touches the look.
//
//   new ContextMenuStewardBehavior(el, steward)   behavior.listen(on)
// =============================================================================

var _SWALLOW_MS = 1000;              // a swallowed gesture that never releases is forgotten after this

class ContextMenuStewardBehavior {
    constructor(el, steward) {
        var self = this;
        this._doc = el.ownerDocument;
        this._win = this._doc.defaultView;
        this._press = function (e) {
            if (!steward._outside(e.target)) return;
            e.stopPropagation();
            e.preventDefault();
            ContextMenuStewardBehavior._swallow(self._doc, e.pointerId);
        };
        this._scroll = function (e) { steward._scrolled(e.target); };
        this._resize = function () { steward._close("scroll"); };
        this._blur = function () { steward._close("blur"); };
    }

    /** Heard while a menu is open, and only then. */
    listen(on) {
        var f = on ? "addEventListener" : "removeEventListener";
        this._doc[f]("pointerdown", this._press, true);
        this._doc[f]("scroll", this._scroll, true);
        this._win[f]("resize", this._resize);
        this._win[f]("blur", this._blur);
    }

    /** The rest of the gesture that closed a menu: its release and its click reach nothing. */
    static _swallow(doc, pointerId) {
        var done = false, timer = null;
        function off() {
            if (done) return;
            done = true;
            if (timer) clearTimeout(timer);
            ["pointerup", "mouseup", "click", "dblclick"].forEach(function (t) { doc.removeEventListener(t, stop, true); });
        }
        function stop(e) {
            if (e.pointerId != null && pointerId != null && e.pointerId !== pointerId) return;
            e.stopPropagation();
            e.preventDefault();
            if (e.type === "pointerup") setTimeout(off, 0);      // the click of this release is still to come, in this task
            else if (e.type === "click") off();                  // its click swallowed: the gesture is over, nothing more of it to come
        }
        ["pointerup", "mouseup", "click", "dblclick"].forEach(function (t) { doc.addEventListener(t, stop, true); });
        timer = setTimeout(off, _SWALLOW_MS);
    }
}
