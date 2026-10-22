// =============================================================================
// TitleBarBehavior — what a title bar does: the hand down on it - the main
// button, anywhere but its close - is its press, and the hand up again,
// wherever it is by then, its release - or the window left while it is down;
// each told to the head. The native focus is taken away from whatever had it,
// and no text is picked by the drag that may follow. A press on its close is
// the close's. It keeps no state of its own and never touches the look.
//
//   new TitleBarBehavior(el, head)
// =============================================================================

class TitleBarBehavior {
    constructor(el, head) {
        var doc = el.ownerDocument, win = doc.defaultView;
        function up(ev) {
            if (ev.type === "mouseup" && ev.button !== 0) return;
            doc.removeEventListener("mouseup", up);
            if (win) win.removeEventListener("blur", up);
            head._released();
        }
        el.addEventListener("mousedown", function (ev) {
            if (ev.button !== 0 || head._onClose(ev.target)) return;
            ev.preventDefault();
            var a = doc.activeElement;
            if (a && a !== doc.body && typeof a.blur === "function") a.blur();
            doc.addEventListener("mouseup", up);
            if (win) win.addEventListener("blur", up);
            head._pressed();
        });
    }
}
