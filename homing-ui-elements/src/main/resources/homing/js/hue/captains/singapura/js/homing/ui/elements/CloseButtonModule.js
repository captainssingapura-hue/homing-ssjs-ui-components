// =============================================================================
// CloseButton — the button that closes what holds it: a Button (a simple
// component) whose face is an icon, never words: the close icon (RFC 0067,
// D52), its part - each style draws its own cross, the same cross a close row
// of a menu shows - made on the room its owner gives it, so nothing here is a
// picture; its words are its name, said to whoever cannot see the icon, and
// shown by the browser to whoever points at it: its tooltip.
//
//   var c = new CloseButton(branch.elementMaker("close"), { face: branch.elementMaker("close-face"), label: "Close the dialog" }, ROLE.STANDALONE);
//   c.face               its icon: the close icon
//   c.label = "Close"    its name: aria-label, and its tooltip; "Close" unless given
//   c.enabled  c.size
//   c.setOnClick(handler)  a press, while it is enabled
// =============================================================================

class CloseButton extends Button {
    static LEAF = "close-button";
    static PARTS = Object.freeze(["face"]);

    constructor(make, props, role) {
        var face = props ? props.face : null;
        if (typeof face !== "function") throw new Error("[CloseButton] its face is the close icon, made on the room its owner gives it: { face: branch.elementMaker(name) }");
        super(make, props, role);
        this._face = new Icon(face(Icon.TAG), ICON.CLOSE_ICON, ROLE.FACE);
        this.el.appendChild(this._face.el);
    }

    get face() { return this._face; }

    get label() { return this.el.getAttribute("aria-label") || ""; }
    set label(words) {
        var name = words == null || words === "" ? "Close" : String(words);
        this.el.setAttribute("aria-label", name);
        this.el.setAttribute("title", name);   // the browser's own tooltip: no caption of the house's to draw
    }
}

/** Its companion: makes the <button> it is realized on, on its owner's branch, under its owner's name. */
const CloseButtonFactory = new SimpleComponentFactory(CloseButton);
