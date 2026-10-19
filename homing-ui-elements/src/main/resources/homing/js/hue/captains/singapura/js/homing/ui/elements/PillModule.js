// =============================================================================
// Pill — a count, kept short, of what cannot all be seen. A Text (a simple component) on a <span>, in the pill's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Pill(branch.elementMaker("x"), new FixedPill(), me.slot(SLOT_…));   in a slot
//   var t = new Pill(branch.elementMaker("x"), new FixedPill());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Pill extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedPill, place ?? managePill); }
}
