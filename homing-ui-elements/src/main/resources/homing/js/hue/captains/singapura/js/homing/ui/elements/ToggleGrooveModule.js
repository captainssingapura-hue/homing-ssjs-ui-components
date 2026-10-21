// =============================================================================
// ToggleGroove — the pill a switch's knob slides in, showing by its colour that
// the switch is on. A simple component on a <span>, in the toggle groove's look;
// its switch sets it on as the switch is.
//
//   var x = new ToggleGroove(branch.elementMaker("toggle-groove"), new FixedToggleGroove(), me.place(FixedToggleGroove));
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class ToggleGroove extends SimpleComponent {
    static TAG = "span";
    static AXES = Object.freeze({
        checked: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            state: StateChecked
        })
    });
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedToggleGroove, place ?? manageToggleGroove); }

    /** On, as its switch is: its Checked state's class. */
    get checked() { return this._get("checked"); }
    set checked(on) { this._set("checked", !!on); }

    /** Turned on or off: by its switch. */
    setOnCheckedChanged(handler) { this._setOn(StateChecked, handler); }
}

