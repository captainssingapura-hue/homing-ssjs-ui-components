// =============================================================================
// Heading5 — the heading of a division within a smaller section. A Heading (a Text) on an
// <h5>, in its own look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Heading5(branch.elementMaker("x"), new FixedHeading5(), me.place(FixedHeading5));   in a slot
//   var t = new Heading5(branch.elementMaker("x"), new FixedHeading5());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Heading5 extends Heading {
    static TAG = "h5";

    constructor(make, fixed, place) { super(make, fixed, FixedHeading5, place ?? manageHeading5); }
}
