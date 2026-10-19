// =============================================================================
// SuccessBadge — a short word saying it went well. A Text (a simple component) on a <span>, in the success badge's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new SuccessBadge(branch.elementMaker("x"), new FixedSuccessBadge(), me.slot(SLOT_…));   in a slot
//   var t = new SuccessBadge(branch.elementMaker("x"), new FixedSuccessBadge());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class SuccessBadge extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedSuccessBadge, place ?? manageSuccessBadge); }
}
