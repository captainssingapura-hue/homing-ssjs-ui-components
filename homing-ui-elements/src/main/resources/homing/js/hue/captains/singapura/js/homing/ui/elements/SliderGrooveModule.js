// =============================================================================
// SliderGroove — the line a slider's value is set along: the whole of its
// range. A simple component on a <div>, in the slider groove's look; the slider
// places it across its rail. It has no state and offers nothing.
//
//   var x = new SliderGroove(branch.elementMaker("slider-groove"), new FixedSliderGroove(), me.place(FixedSliderGroove));
// On the style manager (RFC 0071): its module stamped with its header (D19).
// =============================================================================

class SliderGroove extends SimpleComponent {
    static TAG = "div";
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedSliderGroove, place ?? manageSliderGroove); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }
}

