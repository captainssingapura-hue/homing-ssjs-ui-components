// =============================================================================
// ToggleButton — a button that is on or off: a Button (a simple component) on
// one more axis, on, which a press turns over (ToggleButtonBehavior). On, it
// says so - aria-pressed - which the look's pressed treatments are shown by:
// held as each style presses a button, tinted with the palette's accent.
//
//   var t = new ToggleButton(branch.createElement("bold", ToggleButton.TAG),
//                            { label: "Bold", on: false, onToggle: fn }, ROLE.STANDALONE);
//   t.on = true          on: pressed, and it says so; a press turns it off again
//   t.enabled  t.size  t.label
//   onToggle(on)         in the props: called after a press has turned it over
// =============================================================================

class ToggleButton extends Button {
    static LEAF = "toggle-button";
    static AXES = Object.freeze(Object.assign({}, Button.AXES, {
        on: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-pressed", on ? "true" : "false"); }
        })
    }));
    static BEHAVIOR = ToggleButtonBehavior;

    get on() { return this._get("on"); }
    set on(v) { this._set("on", !!v); }
}
