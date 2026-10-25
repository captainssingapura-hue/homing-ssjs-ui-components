// =============================================================================
// DangerButton — the danger button: a Button whose press destroys or cannot be undone, coloured by its word.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its fixed type and its binding the header's.
//
//   var b = new DangerButton(branch.elementMaker("go"), new FixedDangerButton(), me.place(FixedDangerButton));   in a slot
//   var b = new DangerButton(branch.elementMaker("go"), new FixedDangerButton());                     at the root
//   b.label = "Go"   b.enabled = false   b.size = -1   b.extent = 0.6
//   b.onChange(StateClicks, listener)   a press: its clicks counted up
// =============================================================================

class DangerButton extends Button {
    static DEGREES = Object.freeze([ExtentSize, ExtentColour]);

    constructor(make, fixed, place) { super(make, fixed, FixedDangerButton, place ?? manageDangerButton); }

    get extent() { return this._degree(ExtentColour); }
    set extent(t) { this._setDegree(ExtentColour, t); }
}
