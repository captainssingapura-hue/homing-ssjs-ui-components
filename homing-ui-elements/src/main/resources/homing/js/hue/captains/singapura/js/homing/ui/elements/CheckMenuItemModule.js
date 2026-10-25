// =============================================================================
// CheckMenuItem — a menu's check item (owner, 23 Oct): one choice that is
// checked or not. Made with its own icon or none, and its hint or none, fixed for
// its life; its check always, in its leading place beside its own icon, shown in
// place of it while it is checked (option a). It opens no rows. Whether a pick
// checks it, its menu says. The rest is the base's.
//
//   var item = new CheckMenuItem(branch.createBranch("pin"), new FixedCheckMenuItem(new FixedPinIcon(), null), me.place(FixedCheckMenuItem));
//   item.label = "Pinned"   item.checked = true
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

class CheckMenuItem extends MenuItem {
    static AXES = Object.freeze({
        ...MenuItem.AXES,
        checked: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateChecked })
    });

    constructor(branch, fixed, place) {
        super(branch, fixed, FixedCheckMenuItem, place ?? manageCheckMenuItem, { lead: ROOM_LEAD, label: ROOM_LABEL });
        this.el.setAttribute("role", "menuitemcheckbox");
        this.el.setAttribute("aria-checked", "false");
        // its check, in its leading place beside its own icon: shown while it is checked
        this._check = new Icon(this.branch.elementMaker("check"), new FixedCheckIcon(), this._place(FixedCheckIcon, RoleChosen));
        css.addClass(this._check.el, el_menu_item_off);
        this._lead.appendChild(this._check.el);
    }

    /** Checked: its check shown in its leading place, its own icon not. */
    get checked() { return this._get("checked"); }
    set checked(on) {
        this._set("checked", !!on);
        this.el.setAttribute("aria-checked", this.checked ? "true" : "false");
        css.toggleClass(this._check.el, el_menu_item_off, !this.checked);
        if (this._icon !== null) css.toggleClass(this._icon.el, el_menu_item_off, this.checked);
    }
}
