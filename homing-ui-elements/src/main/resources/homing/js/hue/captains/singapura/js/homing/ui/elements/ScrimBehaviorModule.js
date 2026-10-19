// =============================================================================
// ScrimBehavior — what a veil does: the hand coming down on it is its press,
// told to the veil, whose press it is - as soon as it comes down,
// not when it is let go, so a press that began elsewhere and ends on the veil
// is none of its. It keeps no state of its own, names no node, and never touches the style.
//
//   new ScrimBehavior(el, scrim)
// =============================================================================

class ScrimBehavior {
    constructor(el, scrim) {
        el.addEventListener("mousedown", function (ev) { scrim._pressed(ev); });
    }
}
