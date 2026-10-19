// =============================================================================
// WarningButton — the warning button: a Button whose press asks for care, coloured by its word.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its fixed type and its binding the header's.
//
//   var b = new WarningButton(branch.elementMaker("go"), new FixedWarningButton(), me.slot(SLOT_…));   in a slot
//   var b = new WarningButton(branch.elementMaker("go"), new FixedWarningButton());                     at the root
//   b.label = "Go"   b.enabled = false   b.size = -1   b.extent = 0.6
//   b.setOnClick(handler)
// =============================================================================

class WarningButton extends Button {
    static DEGREES = Object.freeze([ExtentSize, ExtentColour]);

    constructor(make, fixed, place) { super(make, fixed, FixedWarningButton, place ?? manageWarningButton); }

    get extent() { return this._degree(ExtentColour); }
    set extent(t) { this._setDegree(ExtentColour, t); }
}
