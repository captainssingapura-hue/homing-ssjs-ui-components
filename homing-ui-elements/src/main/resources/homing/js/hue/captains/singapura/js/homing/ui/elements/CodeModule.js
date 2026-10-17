// =============================================================================
// Code — code in a line of words, in a fixed face. A Text (a simple component) on a <code>, in the code's look.
//
//   var x = new Code(CodeFactory.create(branch, "code"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Code extends Text {
    static LEAF = "code";
    static TAG = "code";
}

/** Its companion: makes the <code> it is realized on, on its owner's branch, under its owner's name. */
const CodeFactory = new SimpleComponentFactory(Code);
