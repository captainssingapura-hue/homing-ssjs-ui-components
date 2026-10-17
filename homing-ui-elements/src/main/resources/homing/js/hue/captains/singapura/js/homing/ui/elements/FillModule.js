// =============================================================================
// Fill — the part of the range the value has reached. A simple component on a
// <div>, in the fill's look; its owner places it - from its rest to its value.
// It has no state and offers nothing.
//
//   var x = new Fill(branch.elementMaker("fill"), {}, ROLE.LEVEL);
// =============================================================================

class Fill extends SimpleComponent {
    static LEAF = "fill";
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const FillFactory = new SimpleComponentFactory(Fill);
