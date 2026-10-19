// =============================================================================
// Fill — the part of the range the value has reached. A simple component on a
// <div>, in the fill's look; its owner places it - from its rest to its value.
// It has no state and offers nothing.
//
//   var x = new Fill(branch.elementMaker("fill"), new FixedFill(), me.slot(SLOT_…));
// On the style manager (RFC 0071): its module stamped with its header (D19).
// =============================================================================

class Fill extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedFill, place ?? manageFill); }
}

