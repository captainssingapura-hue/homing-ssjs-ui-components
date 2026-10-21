// =============================================================================
// SliderKnob — what is grabbed to move a slider's value along its groove;
// stood up, a fader's cap. A simple component on a <div>, in the slider knob's
// look: its face is its own element's, its mark (an Icon) the slider puts in it.
// Its state is the slider's to set - off, the current of a group, dragged - and
// its standing, given once; each held as its state's class while it holds.
//
//   var x = new SliderKnob(branch.elementMaker("slider-knob"), new FixedSliderKnob(), me.place(FixedSliderKnob));
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class SliderKnob extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: StateEnabled
        }),
        orientation: Object.freeze({
            values: StateOrientation.states,
            rest: StateOrientation.rest,
            reflect: function (el, o) { el.setAttribute("aria-orientation", o); },
            state: StateOrientation
        }),
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: StateCurrent
        }),
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            state: StateDragging
        })
    });
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedSliderKnob, place ?? manageSliderKnob); }

    /** Whether it takes the hand: off, aria-disabled. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** The one of a group, ringed now: aria-current. */
    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** The hand on it, now. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    /** Lying along or stood up: given once, by its slider - fixed for its life, a setter until a fixed type has fields (the port journal). */
    get orientation() { return this._get("orientation"); }
    set orientation(o) { this._set("orientation", String(o)); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }
}

