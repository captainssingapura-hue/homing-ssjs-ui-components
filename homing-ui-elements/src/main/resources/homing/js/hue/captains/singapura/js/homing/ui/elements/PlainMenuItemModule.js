// =============================================================================
// PlainMenuItem — a menu's plain item (owner, 23 Oct): one choice that does
// something, or opens the rows it leads to; never checked. Made with its own
// icon or none, its hint or none, and its way into its rows - the disclose icon -
// or none: fixed for its life. Its rows open (Expanded) only for one made with
// its way on; whether they are, its menu says. The rest is the base's.
//
//   var item = new PlainMenuItem(branch.createBranch("rotate"), new FixedPlainMenuItem(new FixedRotateIcon(), new FixedCaption(), null), me.place(FixedPlainMenuItem));
//   item.label = "Rotate"   item.hint = "a quarter turn"   item.expanded
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

class PlainMenuItem extends MenuItem {
    static AXES = Object.freeze({
        ...MenuItem.AXES,
        expanded: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateExpanded })
    });

    constructor(branch, fixed, place) {
        super(branch, fixed, FixedPlainMenuItem, place ?? managePlainMenuItem, { lead: ROOM_LEAD, label: ROOM_LABEL });
        // its way into its rows, the disclose icon, at its end
        this._disclose = null;
        if (this.fixed.disclose !== null) {
            this._disclose = new Icon(this.branch.elementMaker("disclose"), this.fixed.disclose, this._place(this.fixed.disclose, RoleDisclose));
            css.addClass(this._disclose.el, el_menu_item_disclose);
            this.el.appendChild(this._disclose.el);
            this.el.setAttribute("aria-haspopup", "menu");
            this.el.setAttribute("aria-expanded", "false");
        }
    }

    /** Whether it leads to rows of its own: made with its way on. */
    get opens() { return this._disclose !== null; }

    /** Its rows open. Only a row made with its way on opens rows. */
    get expanded() { return this._get("expanded"); }
    set expanded(on) {
        if (this._disclose === null) throw new TypeError("[PlainMenuItem] made with no way on: it opens no rows");
        this._set("expanded", !!on);
        this.el.setAttribute("aria-expanded", this.expanded ? "true" : "false");
    }
}
