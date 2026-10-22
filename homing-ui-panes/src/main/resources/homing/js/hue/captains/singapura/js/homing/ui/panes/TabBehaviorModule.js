// =============================================================================
// TabBehavior — what a tab does: the hand down on it - the main button - is its
// press, and the hand up again, wherever it is by then, its release - or the
// window left while it is down, which ends it as well; each told to the tab,
// which holds it. The native focus is taken away from whatever had
// it first, since a tab takes none of its own and the keys after the press are
// its bar's; and no text is picked by the drag that may follow. The hand down on
// its cross is the cross's: no press on the tab, and the native focus not taken
// by the cross either. It keeps no state of its own and never touches the look.
//
//   new TabBehavior(el, tab)
// =============================================================================

class TabBehavior {
    constructor(el, tab) {
        var doc = el.ownerDocument, win = doc.defaultView;
        function up(ev) {
            if (ev.type === "mouseup" && ev.button !== 0) return;
            doc.removeEventListener("mouseup", up);
            if (win) win.removeEventListener("blur", up);
            tab._released();
        }
        el.addEventListener("mousedown", function (ev) {
            if (ev.button !== 0) return;
            ev.preventDefault();
            if (tab._onCross(ev.target)) return;
            TabBehavior._letGo(doc);
            doc.addEventListener("mouseup", up);
            if (win) win.addEventListener("blur", up);
            tab._pressed();
        });
    }

    /** The native focus taken away from whatever has it: a press on a tab is its bar's, and the keys after it are too. */
    static _letGo(doc) {
        var a = doc.activeElement;
        if (a && a !== doc.body && typeof a.blur === "function") a.blur();
    }
}
