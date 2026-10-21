// =============================================================================
// CloseButton — the button that closes what holds it: a cross, each style its
// own, named for whoever cannot see it. A Button whose face is a part, the
// close icon (D52) - so a composed component: it takes a branch of its own and
// makes on it its element and its face's, the face placed along its slot by
// the placement its own reference gives. On the style manager (RFC 0071): its
// module stamped with its header (D19).
//
//   var c = new CloseButton(branch.createBranch("close"), new FixedCloseButton(), me.place(FixedCloseButton));
//   c.onChange(StateClicks, listener)   c.clicks   c.enabled   c.size   c.label - its name, "Close" unless given
//   c.face                  its face: the close icon
// =============================================================================

const _closeButtonOwner = Object.freeze({ toString: () => "close-button" });

class CloseButton extends Button {
    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[CloseButton] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_closeButtonOwner);
        super(branch.elementMaker("button"), fixed, FixedCloseButton, place ?? manageCloseButton);
        this._face = new Icon(branch.elementMaker("face"), new FixedCloseIcon(), this._place(FixedCloseIcon));
        this.el.appendChild(this._face.el);
        this.label = "";
    }

    /** Its face: the close icon. */
    get face() { return this._face; }

    get label() { return this.el.getAttribute("aria-label") || ""; }
    set label(words) {
        var name = words == null || words === "" ? "Close" : String(words);
        this.el.setAttribute("aria-label", name);
        this.el.setAttribute("title", name);   // the browser's own tooltip: no caption of the house's to draw
    }
}
