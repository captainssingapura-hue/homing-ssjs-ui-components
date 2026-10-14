// =============================================================================
// SliderKnob — what is grabbed to move a slider's value along its groove;
// stood up, a fader's cap. A simple component on a <div>, in the slider knob's
// look: its face is its own element's, its mark (an Icon) the slider puts in it.
// Its state is the slider's to set - off, the current of a group, dragged - and
// its standing, given once; each held as its state's class while it holds.
//
//   var x = new SliderKnob(branch.elementMaker("knob"), { orientation: "vertical" }, ROLE.THUMB);
// =============================================================================

class SliderKnob extends SimpleComponent {
    static LEAF = "slider-knob";
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: STATE.ENABLED
        }),
        orientation: Object.freeze({
            values: Object.freeze(["horizontal", "vertical"]),
            rest: "horizontal",
            reflect: function (el, o) { el.setAttribute("aria-orientation", o); },
            state: STATE.ORIENTATION
        }),
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: STATE.CURRENT
        }),
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            state: STATE.DRAGGING
        })
    });
    static DEGREES = Object.freeze(["size"]);
    static BEHAVIOR = MarkBehavior;

    /** Whether it takes the hand: off, aria-disabled. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** The one of a group, ringed now: aria-current. */
    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** The hand on it, now. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    /** Lying along or stood up: given once, by its slider. */
    get orientation() { return this._get("orientation"); }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    /** Its enabled changed: by its slider. */
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }

    /** Its standing changed: given once, so never after it is made. */
    setOnOrientationChanged(handler) { this._setOn(STATE.ORIENTATION.changed, handler); }

    /** Made the current of a group, or no longer: by its slider. */
    setOnCurrentChanged(handler) { this._setOn(STATE.CURRENT.changed, handler); }

    /** The hand on it, or off it: by its slider. */
    setOnDraggingChanged(handler) { this._setOn(STATE.DRAGGING.changed, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const SliderKnobFactory = new SimpleComponentFactory(SliderKnob);
