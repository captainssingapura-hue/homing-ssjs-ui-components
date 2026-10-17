// =============================================================================
// Heading2 — the heading of one of a page's great divisions. A Heading (a Text) on an
// <h2>, in its own look.
//
//   var x = new Heading2(Heading2Factory.create(branch, "heading"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Heading2 extends Heading {
    static LEAF = "heading2";
    static TAG = "h2";
}

/** Its companion: makes the <h2> it is realized on, on its owner's branch, under its owner's name. */
const Heading2Factory = new SimpleComponentFactory(Heading2);
