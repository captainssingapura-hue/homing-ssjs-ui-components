// =============================================================================
// ToggleButtonBehavior — what a toggle button does: a press, while it is
// enabled, turns it over - its change fired with the click that made it - and
// then is its click. It changes the state only through the toggle, which writes
// the reflection, keeps none of its own, and never touches the look.
//
//   new ToggleButtonBehavior(el, toggle)
//
// The element's type is "button", so a toggle inside a form does not submit it.
// =============================================================================

class ToggleButtonBehavior {
    constructor(el, toggle) {
        el.type = "button";
        el.addEventListener("click", function (ev) {
            if (!toggle.enabled) return;
            toggle._change("pressed", !toggle.pressed, ev);
            toggle._fire(EVENT.CLICK, ev);
        });
    }
}
