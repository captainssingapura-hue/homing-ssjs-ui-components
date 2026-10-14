// =============================================================================
// Detent — A notch a value may snap to: a line across its owner's groove, shown while what rests
// on it is in the hand (Dragging, data-dragging), as its owner says. A simple component on a <div>, in the
// detent's look; its place on the groove its owner's.
//
//   var detent = new Detent(branch.elementMaker("detent"), {}, ROLE.REST);
//   detent.el  detent.dragging  detent.setOnDraggingChanged(handler)
// =============================================================================

class Detent extends SimpleComponent {
    static LEAF = "detent";
    static TAG = "div";
    static AXES = Object.freeze({
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("data-dragging", ""); else el.removeAttribute("data-dragging"); },
            state: STATE.DRAGGING
        })
    });
    static BEHAVIOR = MarkBehavior;

    /** Shown: what rests on it is in the hand. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    setOnDraggingChanged(handler) { this._setOn(STATE.DRAGGING.changed, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const DetentFactory = new SimpleComponentFactory(Detent);
