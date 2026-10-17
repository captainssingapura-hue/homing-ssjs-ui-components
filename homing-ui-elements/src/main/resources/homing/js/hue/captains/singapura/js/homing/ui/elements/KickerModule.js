// =============================================================================
// Kicker — a few words above a heading that say where it belongs. A Text (a simple component) on a <span>, in the kicker's look.
//
//   var x = new Kicker(KickerFactory.create(branch, "kicker"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Kicker extends Text {
    static LEAF = "kicker";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const KickerFactory = new SimpleComponentFactory(Kicker);
