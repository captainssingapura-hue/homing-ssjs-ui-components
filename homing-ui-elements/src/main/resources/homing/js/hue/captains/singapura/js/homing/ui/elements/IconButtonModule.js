// =============================================================================
// IconButton — the function of a button, the look of an icon: a press does one
// thing, named by the icon it wears and by its words, said to whoever cannot
// see the icon. A Button whose face is a part, an icon - so a composed
// component: it takes a branch of its own and makes on it its element and its
// face's. Which icon its face is, is static (RFC 0071 port, question 5): its
// fixed object holds the icon's own, chosen once for its life, and the face is
// placed as that icon by the placement its own reference gives - in the void
// role, which only places it. It has no role set: what holds it places
// it in the void role, its icon saying what it does. On the style manager
// (RFC 0071): its module stamped with its header (D19).
//
//   var b = new IconButton(branch.createBranch("add"), new FixedIconButton(new FixedAddIcon()), me.place(FixedIconButton));
//   b.label = "Add a tab"   its name: aria-label, and its tooltip
//   b.onChange(StateClicks, listener)   b.clicks   b.enabled   b.size
//   b.face                  its face: the icon its fixed object chose
// =============================================================================

const _iconButtonOwner = Object.freeze({ toString: () => "icon-button" });

class IconButton extends Button {
    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[IconButton] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_iconButtonOwner);
        super(branch.elementMaker("button"), fixed, FixedIconButton, place ?? manageIconButton);
        // its face: the icon its fixed object chose - which it has checked is one of the icons - placed as that icon
        var chosen = this.fixed.voidRole;
        this._face = new Icon(branch.elementMaker("face"), chosen, this._place(chosen));
        this.el.appendChild(this._face.el);
    }

    /** Its face: the icon its fixed object chose. */
    get face() { return this._face; }

    get label() { return this.el.getAttribute("aria-label") || ""; }
    set label(words) {
        var name = words == null ? "" : String(words);
        this.el.setAttribute("aria-label", name);
        this.el.setAttribute("title", name);   // the browser's own tooltip: no caption of the house's to draw
    }
}
