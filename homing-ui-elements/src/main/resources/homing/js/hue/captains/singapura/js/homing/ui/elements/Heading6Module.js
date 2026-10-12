// =============================================================================
// Heading6 — the least heading: a division within that. A Heading (a Text) on an
// <h6>, in its own look.
//
//   var x = new Heading6(Heading6Factory.create(branch, "heading"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Heading6 extends Heading {
    static LEAF = "heading6";
    static TAG = "h6";
}

/** Its companion: makes the <h6> it is realized on, on its owner's branch, under its owner's name. */
const Heading6Factory = new SimpleComponentFactory(Heading6);
