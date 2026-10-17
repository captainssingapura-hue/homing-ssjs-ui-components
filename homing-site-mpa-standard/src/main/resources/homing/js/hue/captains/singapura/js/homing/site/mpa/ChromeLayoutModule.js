// =============================================================================
// ChromeLayout — the site chrome's layout, curated by hand: a layout of its own
// purpose (RFC 0068), not a term of the algebra. A head along the top, kept in
// view as the page scrolls, over the page's slot, which takes the rest.
//
//   var layout = new ChromeLayout(branch, { fill })
//   layout.root        the column: the element its owner attaches
//   layout.head(c)     c along the top - the site's bar: a component, by its
//                      element (el) or its tree (root)
//   layout.slot()      the page's slot: the element the page's app is mounted in
//
// The slot is a reading column - the standard setting, its width and its room
// the layout's - or, where the page says it fills, the whole of the rest: then
// the document is exactly the viewport, pinned, and what scrolls is whatever
// the page scrolls inside itself. A layout is flat: it has no look and draws
// nothing, it places what it is handed. A branch component. `css` is injected
// with the styles import.
// =============================================================================

const _layoutOwner = Object.freeze({ toString: () => "chromeLayout" });

class ChromeLayout {
    constructor(branch, opts) {
        if (!branch) throw new Error("[ChromeLayout] a branch of its own is required");
        branch.activate(_layoutOwner);
        var column = branch.createElement("column", "div");
        css.addClass(column, mpa_root);
        var head = branch.createElement("head", "div");
        css.addClass(head, mpa_head);
        var main = branch.createElement("main", "main");
        css.addClass(main, mpa_main);
        if (opts && opts.fill) css.addClass(main, mpa_main_full);   // the page fills the viewport: it says so, the layout does it
        column.appendChild(head);
        column.appendChild(main);
        this.root = column;
        this._head = head;
        this._main = main;
    }

    head(c) {
        var e = c && (c.root || c.el);
        if (!e) throw new Error("[ChromeLayout] the head holds a component: one with its element, el, or its tree, root");
        this._head.appendChild(e);
        return this;
    }

    slot() { return this._main; }
}
