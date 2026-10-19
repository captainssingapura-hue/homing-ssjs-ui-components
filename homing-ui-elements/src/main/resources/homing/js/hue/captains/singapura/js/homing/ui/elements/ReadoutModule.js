// =============================================================================
// Readout — a value read out exactly, as it stands now. A Text (a simple component) on a <output>, in the readout's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Readout(branch.elementMaker("x"), new FixedReadout(), me.slot(SLOT_…));   in a slot
//   var t = new Readout(branch.elementMaker("x"), new FixedReadout());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Readout extends Text {
    static TAG = "output";

    constructor(make, fixed, place) { super(make, fixed, FixedReadout, place ?? manageReadout); }
}
