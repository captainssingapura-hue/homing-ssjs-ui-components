// =============================================================================
// Wordmark — a name set as the mark of whom it belongs to. A Text (a simple component) on a <span>, in the wordmark's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Wordmark(branch.elementMaker("x"), new FixedWordmark(), me.place(FixedWordmark));   in a slot
//   var t = new Wordmark(branch.elementMaker("x"), new FixedWordmark());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Wordmark extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedWordmark, place ?? manageWordmark); }
}
