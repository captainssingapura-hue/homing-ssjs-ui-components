// =============================================================================
// Heading2 — the heading of one of a page's great divisions. A Heading (a Text) on an
// <h2>, in its own look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Heading2(branch.elementMaker("x"), new FixedHeading2(), me.slot(SLOT_…));   in a slot
//   var t = new Heading2(branch.elementMaker("x"), new FixedHeading2());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Heading2 extends Heading {
    static TAG = "h2";

    constructor(make, fixed, place) { super(make, fixed, FixedHeading2, place ?? manageHeading2); }
}
