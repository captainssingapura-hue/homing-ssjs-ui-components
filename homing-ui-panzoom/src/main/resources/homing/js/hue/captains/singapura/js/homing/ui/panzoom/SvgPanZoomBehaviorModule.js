// =============================================================================
// SvgPanZoomBehavior — what a pan-zoom view does: on its viewport, the wheel,
// the hand pressed, moved and let go, a double press and its scroll - each told
// to the view, its keys the view's own, heard and declared by it - which zooms and pans as its arithmetic says. Made by
// the view's base, which knows its frame alone; handed its viewport once the
// view has made it. It never touches the look.
//
//   new SvgPanZoomBehavior(el, view)   behavior.hear(viewport)
// =============================================================================

class SvgPanZoomBehavior {
    constructor(el, view) { this._view = view; }

    /** Its view's viewport, made: heard from now on. */
    hear(viewport) {
        var view = this._view;
        viewport.addEventListener("wheel", function (ev) { view._wheel(ev); }, { passive: false });
        viewport.addEventListener("scroll", function () { view._scrolled(); });
        viewport.addEventListener("pointerdown", function (ev) { view._press(ev); });
        viewport.addEventListener("pointermove", function (ev) { view._move(ev); });
        viewport.addEventListener("pointerup", function (ev) { view._release(ev); });
        viewport.addEventListener("pointercancel", function (ev) { view._release(ev); });
        viewport.addEventListener("dblclick", function (ev) { view._double(ev); });
    }
}
