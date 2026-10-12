// =============================================================================
// Heading5 — the heading of a division within a smaller section. A Heading (a Text) on an
// <h5>, in its own look.
//
//   var x = new Heading5(Heading5Factory.create(branch, "heading"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Heading5 extends Heading {
    static LEAF = "heading5";
    static TAG = "h5";
}

/** Its companion: makes the <h5> it is realized on, on its owner's branch, under its owner's name. */
const Heading5Factory = new SimpleComponentFactory(Heading5);
