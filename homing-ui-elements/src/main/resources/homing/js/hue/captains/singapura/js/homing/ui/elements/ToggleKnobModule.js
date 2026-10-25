// =============================================================================
// ToggleKnob — what shows which way a switch stands, sliding in its groove as
// it is turned. A simple component on a <span>, in the toggle knob's look; where
// it stands is its switch's placement. It has no state and offers nothing.
//
//   var x = new ToggleKnob(branch.elementMaker("toggle-knob"), new FixedToggleKnob(), me.place(FixedToggleKnob));
// On the style manager (RFC 0071): its module stamped with its header (D19).
// =============================================================================

class ToggleKnob extends SimpleComponent {
    static TAG = "span";
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedToggleKnob, place ?? manageToggleKnob); }
}

