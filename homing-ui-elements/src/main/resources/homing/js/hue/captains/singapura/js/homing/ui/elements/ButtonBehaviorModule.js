// =============================================================================
// ButtonBehavior — what a button does: a press is told to the button, which
// answers it - its click, while it is enabled (Button._pressed); the toggle
// turns over first (ToggleButton._pressed). Shared by the button leaves. It
// keeps no state of its own, names no node, and never touches the style.
//
//   new ButtonBehavior(el, button)
//
// The element's type is "button" unless the caller says otherwise afterwards, so
// a button inside a form does not submit it by accident.
// =============================================================================

class ButtonBehavior {
    constructor(el, button) {
        el.type = "button";
        el.addEventListener("click", function (ev) { button._pressed(); });
    }
}
