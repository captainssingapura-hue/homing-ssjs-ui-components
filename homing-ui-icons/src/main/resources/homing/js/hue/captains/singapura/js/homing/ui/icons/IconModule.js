// =============================================================================
// Icon — an icon of the house's (RFC 0067, D52), as an ELEMENT component: the
// caller mints a span on its branch and hands it in with the icon it is - one
// of ICON, each a leaf of the Icon branch, the house's and every downstream's
// the instance registers - and the role it plays for its owner. The icon wears
// its casting's class, whose look is the style worn's: it draws the picture on
// the icon's ::before, in the colour the icon is given. An icon shows one
// picture: a picture of another shape is another icon, which the owner mounts
// in its place.
//
//   var el = branch.createElement(name, Icon.TAG);
//   var icon = new Icon(el, ICON.CHECK_ICON, role?);   role: what it is to its owner - its face, the keys
//                                                     mark - Standalone unless given; a role an icon plays
//                                                     places it, its look its own
//     icon.el                 the element
//     icon.icon               the icon it is: one of ICON
//     icon.dispose()          its classes off; the element is the caller's
//   Icon.of(token)            the icon a token names - "close-icon" - for a caller that is given one as data
//
// The icon is aria-hidden: what it means is said by the text beside it, or by
// the control's own label — an icon alone is not a label. Its box is a provided
// class, put on through `css`, injected with the styles import.
// =============================================================================
class Icon {
    static TAG = "span";

    static of(token) {
        for (var k in ICON) if (ICON[k].LEAF === token) return ICON[k];
        throw new Error("[Icon] no icon '" + token + "'; one of " + Object.keys(ICON).map(function (k) { return ICON[k].LEAF; }).join(", "));
    }

    static is(icon) {
        for (var k in ICON) if (ICON[k] === icon) return true;
        return false;
    }

    constructor(el, icon, role) {
        if (!el) throw new Error("[Icon] the element is required: mint it with Icon.TAG on your branch");
        if (!Icon.is(icon)) throw new Error("[Icon] an icon is one of ICON, not " + icon);
        this.el = el;
        this.icon = icon;
        this.role = role == null ? ROLE.STANDALONE : role;
        css.addClass(el, ic_base);
        this._look = new Look(icon, this.role);
        this._look.wear(el);   // its casting's class: its picture, in the colour it is given; the keys mark's, the accent carried in by its degree
        el.setAttribute("aria-hidden", "true");
    }

    dispose() {
        css.removeClass(this.el, ic_base);
        if (this._look.leaf) css.removeClass(this.el, css.cls(this._look.leaf.styled));
        this.el.removeAttribute("aria-hidden");
    }
}
