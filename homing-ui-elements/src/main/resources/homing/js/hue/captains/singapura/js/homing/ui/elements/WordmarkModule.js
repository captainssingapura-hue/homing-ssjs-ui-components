// =============================================================================
// Wordmark — a name set as the mark of whom it belongs to. A Text (a simple component) on a <span>, in the wordmark's look.
//
//   var x = new Wordmark(WordmarkFactory.create(branch, "wordmark"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Wordmark extends Text {
    static LEAF = "wordmark";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const WordmarkFactory = new SimpleComponentFactory(Wordmark);
