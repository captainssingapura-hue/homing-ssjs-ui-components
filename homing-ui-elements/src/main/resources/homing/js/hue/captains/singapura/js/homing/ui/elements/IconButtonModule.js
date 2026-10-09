// =============================================================================
// IconButton — a button that is an icon alone: a Button (a simple component)
// whose face is an Icon, its part, made by its caller and handed in. Which
// icon it is is the icon's business, never the button's: the button holds
// whatever icon it is given, and its words are its name, said to whoever
// cannot see the icon.
//
//   var plus = new Icon(branch.createElement("plus", Icon.TAG), { name: "add" });
//   var b = new IconButton(branch.createElement("add", IconButton.TAG),
//                          { icon: plus, label: "Add a tab", onClick: fn }, ROLE.ADD);
//   b.icon               the Icon it holds; set another name on it, not on the button
//   b.label = "Add"      its name: aria-label
//   b.enabled  b.size
//   onClick              in the props: called on a press while enabled
// =============================================================================

class IconButton extends Button {
    static LEAF = "icon-button";

    constructor(el, props, role) {
        var icon = props ? props.icon : null;
        if (!(icon instanceof Icon)) throw new Error("[IconButton] its face is an Icon its caller makes and hands in: { icon }");
        super(el, props, role);
        this._icon = icon;
        el.appendChild(icon.el);
    }

    get icon() { return this._icon; }

    get label() { return this.el.getAttribute("aria-label") || ""; }
    set label(words) { this.el.setAttribute("aria-label", words == null ? "" : String(words)); }
}
