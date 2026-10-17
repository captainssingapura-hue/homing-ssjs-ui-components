// =============================================================================
// BrandMark — the brand's own sign beside its name: a square of its colour,
// until a site gives a logo. A simple component (SimpleComponent) on a <span>,
// in the brand mark's look - its colour is the look's, never its owner's. It
// says nothing to whoever cannot see it (aria-hidden): the brand's name says
// whose site it is. It has no state and offers nothing:
//
//   var m = new BrandMark(BrandMarkFactory.create(branch, "mark"), {}, ROLE.SYMBOL);
//
// Its measure is its owner's, by its own placement.
// =============================================================================

class BrandMark extends SimpleComponent {
    static LEAF = "brand-mark";
    static TAG = "span";
    static BEHAVIOR = MarkBehavior;

    constructor(make, props, role) {
        super(make, props, role);
        this.el.setAttribute("aria-hidden", "true");
    }
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const BrandMarkFactory = new SimpleComponentFactory(BrandMark);
