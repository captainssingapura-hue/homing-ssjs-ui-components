// =============================================================================
// ButtonBehavior — what a button does: a press, while it is enabled, is its
// click, dressed from the DOM's and fired to its handler. Shared by the button
// leaves, the toggle's press its own. It reads the state through the button,
// keeps none of its own, and never touches the look.
//
//   new ButtonBehavior(el, button)
//
// The element's type is "button" unless the caller says otherwise afterwards, so
// a button inside a form does not submit it by accident.
// =============================================================================

class ButtonBehavior {
    constructor(el, button) {
        el.type = "button";
        el.addEventListener("click", function (ev) { if (button.enabled) button._fire(EVENT.CLICK, ev); });
    }
}
