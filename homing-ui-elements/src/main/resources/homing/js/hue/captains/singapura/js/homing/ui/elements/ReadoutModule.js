// =============================================================================
// Readout — a value read out exactly, as it stands now. A Text (a simple component) on a <output>, in the readout's look.
//
//   var x = new Readout(ReadoutFactory.create(branch, "readout"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Readout extends Text {
    static LEAF = "readout";
    static TAG = "output";
}

/** Its companion: makes the <output> it is realized on, on its owner's branch, under its owner's name. */
const ReadoutFactory = new SimpleComponentFactory(Readout);
