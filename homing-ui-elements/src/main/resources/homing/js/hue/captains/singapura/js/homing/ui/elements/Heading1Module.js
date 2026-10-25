// =============================================================================
// Heading1 — a page's own name: the first heading of its outline. A Heading (a Text) on an
// <h1>, in its own look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Heading1(branch.elementMaker("x"), new FixedHeading1(), me.place(FixedHeading1));   in a slot
//   var t = new Heading1(branch.elementMaker("x"), new FixedHeading1());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Heading1 extends Heading {
    static TAG = "h1";

    constructor(make, fixed, place) { super(make, fixed, FixedHeading1, place ?? manageHeading1); }
}
