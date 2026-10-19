// =============================================================================
// SecondaryButton — the secondary button: a Button beside the one that leads, coloured by its word.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its fixed type and its binding the header's.
//
//   var b = new SecondaryButton(branch.elementMaker("go"), new FixedSecondaryButton(), me.slot(SLOT_…));   in a slot
//   var b = new SecondaryButton(branch.elementMaker("go"), new FixedSecondaryButton());                     at the root
//   b.label = "Go"   b.enabled = false   b.size = -1   b.extent = 0.6
//   b.setOnClick(handler)
// =============================================================================

class SecondaryButton extends Button {
    static DEGREES = Object.freeze([ExtentSize, ExtentColour]);

    constructor(make, fixed, place) { super(make, fixed, FixedSecondaryButton, place ?? manageSecondaryButton); }

    get extent() { return this._degree(ExtentColour); }
    set extent(t) { this._setDegree(ExtentColour, t); }
}
