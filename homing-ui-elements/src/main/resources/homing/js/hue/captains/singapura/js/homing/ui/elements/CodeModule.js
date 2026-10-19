// =============================================================================
// Code — code in a line of words, in a fixed face. A Text (a simple component) on a <code>, in the code's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Code(branch.elementMaker("x"), new FixedCode(), me.slot(SLOT_…));   in a slot
//   var t = new Code(branch.elementMaker("x"), new FixedCode());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Code extends Text {
    static TAG = "code";

    constructor(make, fixed, place) { super(make, fixed, FixedCode, place ?? manageCode); }
}
