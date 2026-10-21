// =============================================================================
// CardBehavior — what a card that is a button does: a press is told to it,
// whose click it is (RFC 0071: a behaviour names no node). The card is a button to the
// browser too - its role, and a place in the order the Tab key walks. Enter and
// Space come through the keyboard party, never a listener of its own: the card
// takes them by key(ev). It keeps no state and never touches the look.
//
//   new CardBehavior(el, card)
// =============================================================================

class CardBehavior {
    constructor(el, card) {
        el.setAttribute("role", "button");
        el.tabIndex = 0;
        el.addEventListener("click", function (ev) { card._clicked(); });
    }
}
