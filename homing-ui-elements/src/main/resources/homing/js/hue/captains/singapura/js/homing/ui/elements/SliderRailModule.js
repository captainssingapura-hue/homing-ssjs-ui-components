// =============================================================================
// SliderRail — the stretch a slider's value is set along: the hand taken
// anywhere on it, its groove, fill and knob placed on it by the slider. A simple
// component on a <div>, in the slider rail's look - its measure is its look's,
// grown by its size. The slider sets whether it takes the hand.
//
//   var x = new SliderRail(branch.elementMaker("rail"), {}, ROLE.EDITOR);
// =============================================================================

class SliderRail extends SimpleComponent {
    static LEAF = "slider-rail";
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: STATE.ENABLED
        })
    });
    static DEGREES = Object.freeze(["size"]);
    static BEHAVIOR = MarkBehavior;

    /** Whether it takes the hand: off, aria-disabled. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    /** Its enabled changed: by its slider. */
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const SliderRailFactory = new SimpleComponentFactory(SliderRail);
