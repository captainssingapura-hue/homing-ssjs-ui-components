// =============================================================================
// Heading1 — a page's own name: the first heading of its outline. A Heading (a Text) on an
// <h1>, in its own look.
//
//   var x = new Heading1(Heading1Factory.create(branch, "heading"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Heading1 extends Heading {
    static LEAF = "heading1";
    static TAG = "h1";
}

/** Its companion: makes the <h1> it is realized on, on its owner's branch, under its owner's name. */
const Heading1Factory = new SimpleComponentFactory(Heading1);
