// =============================================================================
// Label — words that name a control or a field, so the user knows what using it will touch. A Text (a simple component) on a <span>, in the label's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Label(branch.elementMaker("x"), new FixedLabel(), me.place(FixedLabel));   in a slot
//   var t = new Label(branch.elementMaker("x"), new FixedLabel());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Label extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedLabel, place ?? manageLabel); }
}
