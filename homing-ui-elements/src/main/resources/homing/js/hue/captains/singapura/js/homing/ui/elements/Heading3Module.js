// =============================================================================
// Heading3 — the heading of a section within a part. A Heading (a Text) on an
// <h3>, in its own look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Heading3(branch.elementMaker("x"), new FixedHeading3(), me.place(FixedHeading3));   in a slot
//   var t = new Heading3(branch.elementMaker("x"), new FixedHeading3());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Heading3 extends Heading {
    static TAG = "h3";

    constructor(make, fixed, place) { super(make, fixed, FixedHeading3, place ?? manageHeading3); }
}
