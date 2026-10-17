// =============================================================================
// Heading4 — the heading of a smaller section, or of what a container holds: a card's, a group's, a setting's title. A Heading (a Text) on an
// <h4>, in its own look.
//
//   var x = new Heading4(Heading4Factory.create(branch, "heading"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Heading4 extends Heading {
    static LEAF = "heading4";
    static TAG = "h4";
}

/** Its companion: makes the <h4> it is realized on, on its owner's branch, under its owner's name. */
const Heading4Factory = new SimpleComponentFactory(Heading4);
