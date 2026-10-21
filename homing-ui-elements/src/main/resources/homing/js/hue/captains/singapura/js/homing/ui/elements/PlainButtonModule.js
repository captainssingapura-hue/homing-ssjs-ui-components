// =============================================================================
// PlainButton — the plain button: a Button in the plain look. Plain is not a semantic word, so it has no extent.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its fixed type and its binding the header's.
//
//   var b = new PlainButton(branch.elementMaker("go"), new FixedPlainButton(), me.place(FixedPlainButton));   in a slot
//   var b = new PlainButton(branch.elementMaker("go"), new FixedPlainButton());                     at the root
//   b.label = "Go"   b.enabled = false   b.size = -1
//   b.setOnClick(handler)
// =============================================================================

class PlainButton extends Button {
    constructor(make, fixed, place) { super(make, fixed, FixedPlainButton, place ?? managePlainButton); }
}
