// =============================================================================
// BoundaryLine — The line where two regions of a layout meet: the layout's own, drawn as its look
// says. Never by itself. Lying or upright (Orientation, aria-orientation) - which way it runs, as its owner
// says; in the hand (Dragging, data-dragging) while its owner's handle moves it. A simple component on a
// <div role="separator">; what the hand does to it - its cursor, its reach,
// its keys - is the handle its owner arms on it.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var line = new BoundaryLine(branch.elementMaker("line-0"), new FixedBoundaryLine(), me.place(FixedBoundaryLine));
//   line.orientation = "vertical"   line.dragging
//   line.setOnOrientationChanged(handler)  line.setOnDraggingChanged(handler)
// =============================================================================

class BoundaryLine extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        orientation: Object.freeze({
            values: StateOrientation.states,
            rest: StateOrientation.rest,
            reflect: function (el, way) { el.setAttribute("aria-orientation", way); },
            state: StateOrientation
        }),
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("data-dragging", ""); else el.removeAttribute("data-dragging"); },
            state: StateDragging
        })
    });
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedBoundaryLine, place ?? manageBoundaryLine);
        this.el.setAttribute("role", "separator");
    }

    /** Which way it runs: horizontal, lying between regions one above the other; vertical, upright between regions side by side. */
    get orientation() { return this._get("orientation"); }
    set orientation(way) { this._set("orientation", String(way)); }

    /** In the hand: its owner's handle moving it. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    setOnOrientationChanged(handler) { this._setOn(StateOrientation, handler); }
    setOnDraggingChanged(handler) { this._setOn(StateDragging, handler); }
}
