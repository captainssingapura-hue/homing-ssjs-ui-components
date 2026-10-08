// =============================================================================
// ButtonBehavior — what a button does: a press acts while it is enabled. Shared
// by the six button leaves. It reads the state through the button, keeps none
// of its own, and never touches the look.
//
//   new ButtonBehavior(el, button, { onClick })
//     onClick   called on a press, while the button is enabled
//
// The element's type is "button" unless the caller says otherwise afterwards, so
// a button inside a form does not submit it by accident.
// =============================================================================

class ButtonBehavior {
    constructor(el, button, props) {
        var onClick = props && typeof props.onClick === "function" ? props.onClick : null;
        el.type = "button";
        if (onClick) el.addEventListener("click", function (ev) { if (button.enabled) onClick(ev); });
    }
}
