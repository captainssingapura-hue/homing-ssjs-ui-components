// =============================================================================
// Grip — what the user takes hold of to move the thing it is on. A simple
// component on a <div>, in the grip's look; its owner moves what it is on.
//
//   var x = new Grip(branch.elementMaker("grip"), {}, ROLE.STANDALONE);
// =============================================================================

class Grip extends SimpleComponent {
    static LEAF = "grip";
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: STATE.ENABLED
        })
    });
    static BEHAVIOR = MarkBehavior;

    /** Whether it takes the hand: off, aria-disabled. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its enabled changed: by its owner. */
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const GripFactory = new SimpleComponentFactory(Grip);
