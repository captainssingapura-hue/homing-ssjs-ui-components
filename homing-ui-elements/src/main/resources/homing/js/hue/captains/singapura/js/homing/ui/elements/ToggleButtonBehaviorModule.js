// =============================================================================
// ToggleButtonBehavior — what a toggle button does: a press, while it is
// enabled, turns it on or off, and says which. It changes the state only
// through the toggle's own setter - which writes the reflection - keeps none
// of its own, and never touches the look.
//
//   new ToggleButtonBehavior(el, toggle, { onToggle })
//     onToggle(on)   called after a press has turned it on or off; never for a
//                    value set in code
//
// The element's type is "button", so a toggle inside a form does not submit it.
// =============================================================================

class ToggleButtonBehavior {
    constructor(el, toggle, props) {
        var onToggle = props && typeof props.onToggle === "function" ? props.onToggle : null;
        el.type = "button";
        el.addEventListener("click", function () {
            if (!toggle.enabled) return;
            toggle.on = !toggle.on;
            if (onToggle) onToggle(toggle.on);
        });
    }
}
