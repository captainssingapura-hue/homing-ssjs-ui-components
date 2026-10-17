// =============================================================================
// SliderGroove — the line a slider's value is set along: the whole of its
// range. A simple component on a <div>, in the slider groove's look; the slider
// places it across its rail. It has no state and offers nothing.
//
//   var x = new SliderGroove(branch.elementMaker("track"), {}, ROLE.SPAN);
// =============================================================================

class SliderGroove extends SimpleComponent {
    static LEAF = "slider-groove";
    static TAG = "div";
    static DEGREES = Object.freeze(["size"]);
    static BEHAVIOR = MarkBehavior;

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const SliderGrooveFactory = new SimpleComponentFactory(SliderGroove);
