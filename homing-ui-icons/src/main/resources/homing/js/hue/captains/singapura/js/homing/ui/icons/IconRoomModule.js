// =============================================================================
// IconRoom — the room an owner keeps for its icons (RFC 0067, D52): it shows
// one icon at a time, the one its owner says. A picture of another shape is
// another icon, so the owner mounts it in the room in place of the one shown;
// each is made once, on the owner's branch, and kept. Which is shown is the
// owner's state: the room only does the mounting. Not a component - the room
// is the owner's element, each icon a component of its own.
//
//   var room = new IconRoom(el, branch, name, role, icons?);
//     el        the owner's element the icons are mounted in
//     branch    the owner's branch: each icon minted on it as name + "-" + its leaf
//     role      what the icons are to their owner - its face, what it measures
//     icons     those it may show; any of ICON unless given
//   room.show(icon)   mount it, the one shown before taken out; null for none
//   room.shown        the icon shown, or null
// =============================================================================
class IconRoom {
    constructor(el, branch, name, role, icons) {
        if (!el || !branch) throw new Error("[IconRoom] the owner's element and its branch are required");
        this.el = el;
        this._branch = branch;
        this._name = name;
        this._role = role;
        this._icons = icons || null;
        this._made = new Map();
        this._shown = null;
    }

    get shown() { return this._shown ? this._shown.icon : null; }

    show(icon) {
        if (icon !== null && (!Icon.is(icon) || (this._icons && this._icons.indexOf(icon) < 0)))
            throw new Error("[IconRoom] its " + this._name + " is " + (this._icons ? "one of " + this._icons.map(function (i) { return i.LEAF; }).join(", ") : "one of ICON")
                            + ", or none - not " + icon);
        if (this._shown) this.el.removeChild(this._shown.el);
        this._shown = null;
        if (icon === null) return null;
        var made = this._made.get(icon);
        if (!made) {
            made = new Icon(this._branch.createElement(this._name + "-" + icon.LEAF, Icon.TAG), icon, this._role);
            this._made.set(icon, made);
        }
        this.el.appendChild(made.el);
        this._shown = made;
        return icon;
    }
}
