// =============================================================================
// MultiTabPaneMenu — a multi-tab pane's tab menu (owner, 23 Oct): a right-press
// on a chip of its bar opens the page's tab menu - through the steward handed to
// the pane - at the pointer, for that tab; the tab in use stays as it is. Its one
// row, Close, asks the tab's close as its cross asks it, and is off for a tab
// that cannot be closed: what a close is, the pane's caller decides. A
// right-press anywhere else on the pane - a floater's chip among it - is the
// browser's. The steward keeps one handler for a kind, the page's: every pane
// hands it the same one, and a menu is bound to the pane's menu that opened it
// and the tab. Detach - a move of the tab to another pane - waits.
//
//   new MultiTabPaneMenu(pane, strip, steward)   menu.ask(target, x, y) → whether it opened   menu.drop()
// =============================================================================

/** The page's handler of the tab menu, the same for every pane: a pick, and what depends on the tab, asked of the pane's menu bound. */
const TAB_MENU_HANDLER = Object.freeze({
    pick(row, bound) { bound.menu._picked(row, bound.tab); },
    state(row, bound) { return bound.menu._state(row, bound.tab); }
});

class MultiTabPaneMenu {
    constructor(pane, strip, steward) {
        if (!steward || typeof steward.has !== "function" || !steward.has("tab"))
            throw new TypeError("[MultiTabPane] its menus' steward holds no tab menu: the page's registry, with the kinds the pane needs");
        this._pane = pane;
        this._strip = strip;
        this._steward = steward;
        steward.handle("tab", TAB_MENU_HANDLER);
        Object.freeze(this);
    }

    /** A right-press on the pane: on a chip of its bar, that tab's menu at the pointer. */
    ask(target, x, y) {
        var id = this._strip.tabAt(target);
        if (id === null) return false;
        return this._steward.open("tab", Object.freeze({ menu: this, tab: id }), { x: x, y: y }, { anchor: this._pane.root });
    }

    /** A row picked: Close, the tab's close asked - while it is there, and can be closed. */
    _picked(row, id) {
        if (row === "close" && this._pane.order.indexOf(id) >= 0 && this._pane.closes[id] != null) this._strip.closeAsked(id);
    }

    /** What depends on the tab: Close off for one that cannot be closed. */
    _state(row, id) { return row === "close" ? { disabled: this._pane.closes[id] == null } : {}; }

    /** Gone with its pane: a menu it opened closed. */
    drop() {
        var bound = this._steward.bound();
        if (bound !== null && bound.menu === this) this._steward.close();
    }
}
