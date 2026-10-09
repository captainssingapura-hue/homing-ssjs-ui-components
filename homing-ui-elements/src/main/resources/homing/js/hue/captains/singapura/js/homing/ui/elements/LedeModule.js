// =============================================================================
// Lede — the first words of a body, saying what follows in brief. A Text (a simple component) on a <p>, in the lede's look.
//
//   var x = new Lede(LedeFactory.create(branch, "lede"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Lede extends Text {
    static LEAF = "lede";
    static TAG = "p";
}

/** Its companion: makes the <p> it is realized on, on its owner's branch, under its owner's name. */
const LedeFactory = new SimpleComponentFactory(Lede);
