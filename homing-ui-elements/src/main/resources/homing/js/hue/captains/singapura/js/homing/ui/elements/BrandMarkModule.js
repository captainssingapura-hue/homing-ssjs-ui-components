// =============================================================================
// BrandMark — the brand's own sign beside its name: a square of its colour,
// until a site gives a logo. A simple component (SimpleComponent) on a <span>;
// its colour is the style's, never its owner's. It
// says nothing to whoever cannot see it (aria-hidden): the brand's name says
// whose site it is. It has no state and offers nothing.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var m = new BrandMark(branch.elementMaker("x"), new FixedBrandMark(), me.slot(SLOT_…));
// =============================================================================

class BrandMark extends SimpleComponent {
    static TAG = "span";
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedBrandMark, place ?? manageBrandMark);
        this.el.setAttribute("aria-hidden", "true");
    }
}
