// =============================================================================
// Heading3 — the heading of a section within a part. A Heading (a Text) on an
// <h3>, in its own look.
//
//   var x = new Heading3(Heading3Factory.create(branch, "heading"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Heading3 extends Heading {
    static LEAF = "heading3";
    static TAG = "h3";
}

/** Its companion: makes the <h3> it is realized on, on its owner's branch, under its owner's name. */
const Heading3Factory = new SimpleComponentFactory(Heading3);
