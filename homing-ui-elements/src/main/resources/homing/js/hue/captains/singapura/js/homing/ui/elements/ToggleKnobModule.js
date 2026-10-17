// =============================================================================
// ToggleKnob — what shows which way a switch stands, sliding in its groove as
// it is turned. A simple component on a <span>, in the toggle knob's look; where
// it stands is its switch's placement. It has no state and offers nothing.
//
//   var x = new ToggleKnob(branch.elementMaker("knob"), {}, ROLE.THUMB);
// =============================================================================

class ToggleKnob extends SimpleComponent {
    static LEAF = "toggle-knob";
    static TAG = "span";
    static BEHAVIOR = MarkBehavior;
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const ToggleKnobFactory = new SimpleComponentFactory(ToggleKnob);
