// =============================================================================
// Grip — what the user takes hold of to move the thing it is on. A simple
// component on a <div>, in the grip's look; its owner moves what it is on.
//
//   var x = new Grip(branch.elementMaker("grip"), new FixedGrip(), me.place(FixedGrip));
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class Grip extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: StateEnabled
        })
    });
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedGrip, place ?? manageGrip); }

    /** Whether it takes the hand: off, aria-disabled. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }
}

