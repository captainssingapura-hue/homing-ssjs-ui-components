// =============================================================================
// Tick — A scale's tick: a short line in the colour it is given, its place on the scale its owner's.
// A simple component on a <div>.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var m = new Tick(branch.elementMaker("x"), new FixedTick(), me.place(FixedTick));
// =============================================================================

class Tick extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedTick, place ?? manageTick);
        this.el.setAttribute("aria-hidden", "true");
    }
}
