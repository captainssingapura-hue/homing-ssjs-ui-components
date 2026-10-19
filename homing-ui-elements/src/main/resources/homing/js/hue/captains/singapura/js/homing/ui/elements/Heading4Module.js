// =============================================================================
// Heading4 — the heading of a smaller section, or of what a container holds: a card's, a group's, a setting's title. A Heading (a Text) on an
// <h4>, in its own look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Heading4(branch.elementMaker("x"), new FixedHeading4(), me.slot(SLOT_…));   in a slot
//   var t = new Heading4(branch.elementMaker("x"), new FixedHeading4());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Heading4 extends Heading {
    static TAG = "h4";

    constructor(make, fixed, place) { super(make, fixed, FixedHeading4, place ?? manageHeading4); }
}
