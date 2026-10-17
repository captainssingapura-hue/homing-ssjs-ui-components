// =============================================================================
// TabBehavior — what a tab does: the hand down on it, not on its cross, is its
// Press - the native focus taken away from whatever had it first, since a tab
// takes none of its own and the keys after the press are its holder's; and a
// right-click on it asks the opener its owner gave it for its menu, the
// browser's own suppressed only when one opened. It keeps no state and never
// touches the look.
//
//   new TabBehavior(el, tab)
// =============================================================================

class TabBehavior {
    constructor(el, tab) {
        el.addEventListener("pointerdown", function (ev) {
            if (ev.button !== 0 || tab.crosses(ev.target)) return;
            TabBehavior._letGo();
            tab._fire(EVENT.PRESS, ev);
        });
        el.addEventListener("contextmenu", function (ev) {
            if (tab._menu && tab._menu.open({ x: ev.clientX, y: ev.clientY }, false)) ev.preventDefault();
        });
    }

    /** The native focus taken away from whatever has it: a press on a tab is its holder's, and the keys after it are too. */
    static _letGo() {
        if (typeof document === "undefined") return;
        var a = document.activeElement;
        if (a && a !== document.body && typeof a.blur === "function") a.blur();
    }
}
