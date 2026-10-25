// =============================================================================
// TextCell — a table cell's words: plain text set in its cell, a fact in its row or the name heading its column. A Text
// (a simple component) on a <div>, in the text cell's look; its room is its holder's (RFC 0067, D42).
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new TextCell(branch.elementMaker("x"), new FixedTextCell(), me.place(FixedTextCell));   in a slot
//   var t = new TextCell(branch.elementMaker("x"), new FixedTextCell());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class TextCell extends Text {
    static TAG = "div";

    constructor(make, fixed, place) { super(make, fixed, FixedTextCell, place ?? manageTextCell); }
}
