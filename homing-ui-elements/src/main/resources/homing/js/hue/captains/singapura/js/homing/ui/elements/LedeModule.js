// =============================================================================
// Lede — the first words of a body, saying what follows in brief. A Text (a simple component) on a <p>, in the lede's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Lede(branch.elementMaker("x"), new FixedLede(), me.slot(SLOT_…));   in a slot
//   var t = new Lede(branch.elementMaker("x"), new FixedLede());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Lede extends Text {
    static TAG = "p";

    constructor(make, fixed, place) { super(make, fixed, FixedLede, place ?? manageLede); }
}
