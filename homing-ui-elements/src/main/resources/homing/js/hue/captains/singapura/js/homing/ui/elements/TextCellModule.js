// =============================================================================
// TextCell — a table cell's words: plain text set in its cell, a fact in its row or the name heading its column. A Text
// (a simple component) on a <div>, in the text cell's look; its room is its holder's (RFC 0067, D42).
//
//   var x = new TextCell(TextCellFactory.create(branch, "cell"), { text: "…" }, ROLE.VALUE);   // or ROLE.TITLE, ROLE.STANDALONE
//   x.text  x.size
// =============================================================================

class TextCell extends Text {
    static LEAF = "text-cell";
    static TAG = "div";
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const TextCellFactory = new SimpleComponentFactory(TextCell);
