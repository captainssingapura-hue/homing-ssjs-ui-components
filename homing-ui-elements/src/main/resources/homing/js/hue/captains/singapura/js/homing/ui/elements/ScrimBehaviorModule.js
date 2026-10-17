// =============================================================================
// ScrimBehavior — what a veil does: the hand coming down on it is its press,
// dressed from the DOM's and fired to its handler - as soon as it comes down,
// not when it is let go, so a press that began elsewhere and ends on the veil
// is none of its. It keeps no state of its own and never touches the look.
//
//   new ScrimBehavior(el, scrim)
// =============================================================================

class ScrimBehavior {
    constructor(el, scrim) {
        el.addEventListener("mousedown", function (ev) { scrim._fire(EVENT.PRESS, ev); });
    }
}
