// =============================================================================
// ToggleButton — a Button that holds a pressed state: a press while it is
// enabled turns it over, then is its click. On the style manager (RFC 0071):
// its module stamped with its header (D19), its pressed set through its
// reference, which the style draws.
//
//   var t = new ToggleButton(branch.elementMaker("bold"), new FixedToggleButton(), me.place(FixedToggleButton));
//   t.pressed = true      t.onChange(StatePressed, listener)   t.setOnClick(handler)
// =============================================================================

class ToggleButton extends Button {
    static AXES = Object.freeze(Object.assign({}, Button.AXES, {
        pressed: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-pressed", on ? "true" : "false"); },
            state: StatePressed
        })
    }));
    static BEHAVIOR = ToggleButtonBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedToggleButton, place ?? manageToggleButton); }

    get pressed() { return this._get("pressed"); }
    set pressed(v) { this._set("pressed", !!v); }

    /** A press, as its behaviour hears it: while it is enabled, its pressed turned over, then its click. */
    _pressed(ev) {
        if (!this.enabled) return;
        this._change("pressed", !this.pressed);
        this._fire(EventClick, ev);
    }
}
