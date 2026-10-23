// =============================================================================
// ContextMenu — a menu opened where it is asked: level 0, the one at the top
// (owner, 23 Oct). Its rows, made once from its kind's tree, and the level-1 menu
// of each row that opens rows, beside it: the rest is the levels' base. Shown at a
// point in a layer over the page - its steward's - and hidden; given the keys
// while it is open, left and right its own even where nothing is beneath them.
//
//   var menu = new ContextMenu(branch.createBranch("type-tab"), new FixedContextMenu(), me.place(FixedContextMenu, RolePopup));
//   menu.hold(type.nodes, type.kind)   menu.bind(object, state)
//   menu.show(layer, at, viewport, fromKeyboard) → { x, y }   menu.hide()
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

class ContextMenu extends Menu {
    constructor(branch, fixed, place) {
        super(branch, fixed, FixedContextMenu, place ?? manageContextMenu, [L1Menu, FixedL1Menu]);
    }

    /** Shown in a layer at a point - kept within the viewport - and focused; its first usable row pointed when opened by the keys. */
    show(layer, at, viewport, fromKeyboard) {
        layer.appendChild(this.el);
        var r = this.el.getBoundingClientRect();
        var p = MenuGeometry.place(at, { w: r.width, h: r.height }, viewport);
        this.el.style.setProperty("--cm-x", p.x + "px");
        this.el.style.setProperty("--cm-y", p.y + "px");
        try { this.el.focus({ preventScroll: true }); } catch (e) {}
        if (fromKeyboard) this._move(1, true);
        return p;
    }

    /** Hidden: its rows closed, its cursor let go. */
    hide() { this._reset(); if (this.el.parentNode) this.el.parentNode.removeChild(this.el); }

    /** The keys while it is open: the left and right arrows its own at the top, even where nothing is beneath them. */
    key(ev) { return super.key(ev) || ev.key === "ArrowLeft" || ev.key === "ArrowRight"; }
}
