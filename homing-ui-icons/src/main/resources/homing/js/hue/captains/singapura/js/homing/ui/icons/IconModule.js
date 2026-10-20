// =============================================================================
// Icon — an icon of the house's (RFC 0067, D52), as an ELEMENT component, on the
// style manager (RFC 0071): one class for every leaf of the Icon branch - the
// house's and every downstream's - alike but for their look. It makes its own
// element, a span, with the maker its owner's branch issues, and is made as the
// leaf whose fixed object it is handed: new FixedCloseIcon() makes the close
// icon. It places itself with the placement its owner hands it for the slot it
// is in, or at the root with that leaf's own binding; its casting's class is
// the style manager's, and so is its picture: what the leaf shows (its content,
// D56) drawn on its element from the icon palette of the style worn, by the
// style manager, which redraws it when a switch draws it otherwise - the icon
// writes nothing in its element. Its look, the same for every icon, gives it
// the colour it is given. An icon shows one picture: a picture of another shape
// is another icon, which the owner makes in its place.
//
// Its module is stamped with every icon leaf's header, merged (D19), and the
// leaves it realizes - each one's node, fixed type and binding (LEAVES_IMPLEMENTED).
//
//   var icon = new Icon(branch.elementMaker("cross"), new FixedCloseIcon(), me.slot(SLOT_…));
//     icon.el          the element, for its owner to lay out
//     icon.icon        the icon it is: its leaf's node, ComponentCloseIcon
//     icon.extent = 1  how much of its colour's meaning it carries (the keys mark's degree)
//
// The icon is aria-hidden: what it means is said by the text beside it, or by
// the control's own label — an icon alone is not a label. Its box is a provided
// class, put on through `css`, injected with the styles import.
// =============================================================================

/** Each leaf it realizes by its fixed type: its node, and its binding - none for one only ever a part. */
const _ICON_LEAVES = new Map(LEAVES_IMPLEMENTED.map(([component, fixed, binding]) => [fixed, Object.freeze({ component, binding })]));

class Icon {
    static DEGREES = Object.freeze([ExtentColour]);
    static TAG = "span";
    #styles;
    #leaf;
    #extent = 1;

    /**
     * @param make  the maker its owner's branch issues
     * @param fixed the fixed object of the leaf it is made as - which icon it is, for its life
     * @param place the placement its owner hands it for its slot; none at the root, where it places itself
     */
    constructor(make, fixed, place) {
        const Fixed = fixed !== null && typeof fixed === "object" ? Object.getPrototypeOf(fixed).constructor : undefined;
        const leaf = _ICON_LEAVES.get(Fixed);
        if (leaf === undefined) throw new TypeError("[Icon] what is fixed for its life is which icon it is: the fixed object of one of its leaves - new FixedCloseIcon() - never a bag of named values");
        if (!DomOpsParty.isElementMaker(make)) throw new TypeError("[Icon] takes the maker its owner's branch issues - branch.elementMaker(name) - and no other function, nor an element");
        const at = place ?? leaf.binding;
        if (typeof at !== "function") throw new TypeError("[Icon] " + leaf.component.token + " is placed by the placement its owner hands it, and has no binding of its own: it is only ever a part");
        this.el = make(Icon.TAG);
        this.fixed = fixed;
        this.#leaf = leaf;
        this.#styles = at(this.el);
        css.addClass(this.el, ic_base);
        this.el.setAttribute("aria-hidden", "true");
    }

    /** The icon it is: its leaf's node. */
    get icon() { return this.#leaf.component; }

    /** How much of its colour's meaning it carries: 1, its rest, at full; 0 none; −1 turned the other way. */
    get extent() { return this.#extent; }
    set extent(t) {
        const x = Math.max(-1, Math.min(1, Number(t)));
        this.#extent = Number.isFinite(x) ? x : 1;
        css.extent(this.el, this.#extent === 1 ? null : this.#extent);
    }
}
