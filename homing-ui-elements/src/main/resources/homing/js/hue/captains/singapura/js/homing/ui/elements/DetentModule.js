// =============================================================================
// Detent — A notch a value may snap to: a line across its owner's groove, shown while what rests
// on it is in the hand (Dragging, data-dragging), as its owner says. A simple component on a <div>, in the
// detent's look; its place on the groove its owner's.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var detent = new Detent(branch.elementMaker("detent"), new FixedDetent(), me.place(FixedDetent));
//   detent.dragging  detent.onChange(StateDragging, listener)
// =============================================================================

class Detent extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("data-dragging", ""); else el.removeAttribute("data-dragging"); },
            state: StateDragging
        })
    });
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedDetent, place ?? manageDetent); }

    /** Shown: what rests on it is in the hand. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }
}
