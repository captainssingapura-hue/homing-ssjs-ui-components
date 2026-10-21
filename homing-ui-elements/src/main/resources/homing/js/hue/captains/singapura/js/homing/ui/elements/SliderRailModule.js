// =============================================================================
// SliderRail — the stretch a slider's value is set along: the hand taken
// anywhere on it, its groove, fill and knob placed on it by the slider. A simple
// component on a <div>, in the slider rail's look - its measure is its look's,
// grown by its size. The slider sets whether it takes the hand.
//
//   var x = new SliderRail(branch.elementMaker("slider-rail"), new FixedSliderRail(), me.place(FixedSliderRail));
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class SliderRail extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: StateEnabled
        })
    });
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedSliderRail, place ?? manageSliderRail); }

    /** Whether it takes the hand: off, aria-disabled. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }
}

