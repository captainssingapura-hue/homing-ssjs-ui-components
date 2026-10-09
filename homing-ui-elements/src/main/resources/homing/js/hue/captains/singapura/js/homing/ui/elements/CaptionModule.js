// =============================================================================
// Caption — quiet words beside something, adding to it without competing with it. A Text (a simple component) on a <span>, in the caption's look.
//
//   var x = new Caption(CaptionFactory.create(branch, "caption"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Caption extends Text {
    static LEAF = "caption";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const CaptionFactory = new SimpleComponentFactory(Caption);
