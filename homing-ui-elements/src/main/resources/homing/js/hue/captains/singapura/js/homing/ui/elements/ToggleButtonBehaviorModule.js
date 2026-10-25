// =============================================================================
// ToggleButtonBehavior — what a toggle button does: a press is told to it, which
// answers it - turned over, then its click, while it is enabled
// (ToggleButton._pressed). It keeps no state of its own, names no node, and
// never touches the style.
//
//   new ToggleButtonBehavior(el, toggle)
// =============================================================================

class ToggleButtonBehavior {
    constructor(el, toggle) {
        el.type = "button";
        el.addEventListener("click", function (ev) { toggle._pressed(); });
    }
}
