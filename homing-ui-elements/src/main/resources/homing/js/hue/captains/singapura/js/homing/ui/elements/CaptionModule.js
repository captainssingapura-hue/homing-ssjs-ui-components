// =============================================================================
// Caption — quiet words beside something, adding to it without competing with it. A Text (a simple component) on a <span>, in the caption's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Caption(branch.elementMaker("x"), new FixedCaption(), me.slot(SLOT_…));   in a slot
//   var t = new Caption(branch.elementMaker("x"), new FixedCaption());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Caption extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedCaption, place ?? manageCaption); }
}
