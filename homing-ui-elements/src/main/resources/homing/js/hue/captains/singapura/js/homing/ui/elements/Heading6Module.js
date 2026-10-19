// =============================================================================
// Heading6 — the least heading: a division within that. A Heading (a Text) on an
// <h6>, in its own look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Heading6(branch.elementMaker("x"), new FixedHeading6(), me.slot(SLOT_…));   in a slot
//   var t = new Heading6(branch.elementMaker("x"), new FixedHeading6());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Heading6 extends Heading {
    static TAG = "h6";

    constructor(make, fixed, place) { super(make, fixed, FixedHeading6, place ?? manageHeading6); }
}
