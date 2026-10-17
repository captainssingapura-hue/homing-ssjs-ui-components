// =============================================================================
// Tick — A scale's tick: a short line in the colour it is given, its place on the scale its owner's.
// A simple component on a <div>, in the tick's look.
//
//   var tick = new Tick(branch.elementMaker("tick-0-line"), {}, ROLE.NOTCH);
//   tick.el
// =============================================================================

class Tick extends SimpleComponent {
    static LEAF = "tick";
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(make, props, role) {
        super(make, props, role);
        this.el.setAttribute("aria-hidden", "true");
    }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const TickFactory = new SimpleComponentFactory(Tick);
