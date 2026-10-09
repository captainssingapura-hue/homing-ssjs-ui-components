// =============================================================================
// Pill — a count, kept short, of what cannot all be seen. A Text (a simple component) on a <span>, in the pill's look.
//
//   var x = new Pill(PillFactory.create(branch, "pill"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Pill extends Text {
    static LEAF = "pill";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const PillFactory = new SimpleComponentFactory(Pill);
