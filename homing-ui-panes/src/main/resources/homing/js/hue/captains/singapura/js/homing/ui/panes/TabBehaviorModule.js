// =============================================================================
// TabBehavior — what a tab does: the hand down on it - the main button - is its
// press, and the hand up again, wherever it is by then, its release; each told
// to the tab, which holds it. The native focus is taken away from whatever had
// it first, since a tab takes none of its own and the keys after the press are
// its bar's; and no text is picked by the drag that may follow. It keeps no
// state of its own and never touches the look.
//
//   new TabBehavior(el, tab)
// =============================================================================

class TabBehavior {
    constructor(el, tab) {
        var doc = el.ownerDocument;
        function up(ev) {
            if (ev.button !== 0) return;
            doc.removeEventListener("mouseup", up);
            tab._released();
        }
        el.addEventListener("mousedown", function (ev) {
            if (ev.button !== 0) return;
            ev.preventDefault();
            TabBehavior._letGo(doc);
            doc.addEventListener("mouseup", up);
            tab._pressed();
        });
    }

    /** The native focus taken away from whatever has it: a press on a tab is its bar's, and the keys after it are too. */
    static _letGo(doc) {
        var a = doc.activeElement;
        if (a && a !== doc.body && typeof a.blur === "function") a.blur();
    }
}
