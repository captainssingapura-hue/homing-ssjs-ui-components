// =============================================================================
// LinkBehavior — what a way somewhere does: a click on it is its click,
// dressed from the DOM's and fired to its handler. Shared by the link and the
// crumb. The way is followed by the browser, as a link's is: a handler that
// wants it otherwise has the raw event to prevent. It keeps no state of its own
// and never touches the look.
//
//   new LinkBehavior(el, link)
// =============================================================================

class LinkBehavior {
    constructor(el, link) {
        el.addEventListener("click", function (ev) { link._fire(EVENT.CLICK, ev); });
    }
}
