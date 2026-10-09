// =============================================================================
// ToggleButton — a button that is pressed or not: a Button (a simple component)
// with one more state, pressed, which a press turns over (ToggleButtonBehavior).
// Pressed, it says so - aria-pressed - which the look's pressed treatments are
// shown by: held as each style presses a button, tinted with the palette's accent.
//
//   var t = new ToggleButton(ToggleButtonFactory.create(branch, "bold"),
//                            { label: "Bold", pressed: false }, ROLE.STANDALONE);
//   t.pressed = true               pressed, and it says so; a press turns it over again
//   t.setOnPressedChanged(handler) its pressed changed: handler.handle(StateChange) -
//                                  previous the old value, raw the click that turned
//                                  it, or null when a setter did
//   t.setOnClick(handler)          a press, after it has turned over
//   t.enabled  t.size  t.label  t.setOnEnabledChanged
// =============================================================================

class ToggleButton extends Button {
    static LEAF = "toggle-button";
    static AXES = Object.freeze(Object.assign({}, Button.AXES, {
        pressed: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-pressed", on ? "true" : "false"); },
            state: STATE.PRESSED
        })
    }));
    static BEHAVIOR = ToggleButtonBehavior;

    get pressed() { return this._get("pressed"); }
    set pressed(v) { this._set("pressed", !!v); }

    /** Its pressed changed: by a press, or by a setter. */
    setOnPressedChanged(handler) { this._setOn(STATE.PRESSED.changed, handler); }
}

/** Its companion: makes the <button> it is realized on, on its owner's branch, under its owner's name. */
const ToggleButtonFactory = new SimpleComponentFactory(ToggleButton);
