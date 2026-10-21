// =============================================================================
// SuccessButton — the success button: a Button whose press confirms, coloured by its word.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its fixed type and its binding the header's.
//
//   var b = new SuccessButton(branch.elementMaker("go"), new FixedSuccessButton(), me.place(FixedSuccessButton));   in a slot
//   var b = new SuccessButton(branch.elementMaker("go"), new FixedSuccessButton());                     at the root
//   b.label = "Go"   b.enabled = false   b.size = -1   b.extent = 0.6
//   b.setOnClick(handler)
// =============================================================================

class SuccessButton extends Button {
    static DEGREES = Object.freeze([ExtentSize, ExtentColour]);

    constructor(make, fixed, place) { super(make, fixed, FixedSuccessButton, place ?? manageSuccessButton); }

    get extent() { return this._degree(ExtentColour); }
    set extent(t) { this._setDegree(ExtentColour, t); }
}
