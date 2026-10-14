// =============================================================================
// BoundaryLine — The line where two regions of a layout meet: the layout's own, drawn as its look
// says. Never by itself. Lying or upright (Orientation, aria-orientation) - which way it runs, as its owner
// says; in the hand (Dragging, data-dragging) while its owner's handle moves it. A simple component on a
// <div role="separator">, in the boundary line's look; what the hand does to it - its cursor, its reach,
// its keys - is the handle its owner arms on it.
//
//   var line = new BoundaryLine(branch.elementMaker("line-0"), { orientation: "vertical" }, ROLE.SPLITTER);
//   line.el  line.orientation  line.dragging
//   line.setOnOrientationChanged(handler)  line.setOnDraggingChanged(handler)
// =============================================================================

class BoundaryLine extends SimpleComponent {
    static LEAF = "boundary-line";
    static TAG = "div";
    static AXES = Object.freeze({
        orientation: Object.freeze({
            values: STATE.ORIENTATION.values,
            rest: STATE.ORIENTATION.rest,
            reflect: function (el, way) { el.setAttribute("aria-orientation", way); },
            state: STATE.ORIENTATION
        }),
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("data-dragging", ""); else el.removeAttribute("data-dragging"); },
            state: STATE.DRAGGING
        })
    });
    static BEHAVIOR = MarkBehavior;

    constructor(make, props, role) {
        super(make, props, role);
        this.el.setAttribute("role", "separator");
    }

    /** Which way it runs: horizontal, lying between regions one above the other; vertical, upright between regions side by side. */
    get orientation() { return this._get("orientation"); }
    set orientation(way) { this._set("orientation", String(way)); }

    /** In the hand: its owner's handle moving it. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    setOnOrientationChanged(handler) { this._setOn(STATE.ORIENTATION.changed, handler); }
    setOnDraggingChanged(handler) { this._setOn(STATE.DRAGGING.changed, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const BoundaryLineFactory = new SimpleComponentFactory(BoundaryLine);
