// =============================================================================
// Kicker — a few words above a heading that say where it belongs. A Text (a simple component) on a <span>, in the kicker's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Kicker(branch.elementMaker("x"), new FixedKicker(), me.slot(SLOT_…));   in a slot
//   var t = new Kicker(branch.elementMaker("x"), new FixedKicker());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Kicker extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedKicker, place ?? manageKicker); }
}
