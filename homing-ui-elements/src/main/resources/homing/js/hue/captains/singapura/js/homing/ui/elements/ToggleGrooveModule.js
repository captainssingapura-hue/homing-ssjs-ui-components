// =============================================================================
// ToggleGroove — the pill a switch's knob slides in, showing by its colour that
// the switch is on. A simple component on a <span>, in the toggle groove's look;
// its switch sets it on as the switch is.
//
//   var x = new ToggleGroove(branch.elementMaker("groove"), { checked: true }, ROLE.SPAN);
// =============================================================================

class ToggleGroove extends SimpleComponent {
    static LEAF = "toggle-groove";
    static TAG = "span";
    static AXES = Object.freeze({
        checked: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            state: STATE.CHECKED
        })
    });
    static BEHAVIOR = MarkBehavior;

    /** On, as its switch is: its Checked state's class. */
    get checked() { return this._get("checked"); }
    set checked(on) { this._set("checked", !!on); }

    /** Turned on or off: by its switch. */
    setOnCheckedChanged(handler) { this._setOn(STATE.CHECKED.changed, handler); }
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const ToggleGrooveFactory = new SimpleComponentFactory(ToggleGroove);
