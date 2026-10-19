// =============================================================================
// LinkBehavior — what a way somewhere does: a click on it is told to it, whose
// click it is (RFC 0071: a behaviour names no node). Shared by the link and the
// crumb. The way is followed by the browser, as a link's is: a handler that
// wants it otherwise has the raw event to prevent. It keeps no state of its own
// and never touches the style.
//
//   new LinkBehavior(el, link)
// =============================================================================

class LinkBehavior {
    constructor(el, link) {
        el.addEventListener("click", function (ev) { link._clicked(ev); });
    }
}
