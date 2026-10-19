// =============================================================================
// PlainCard — the basic card: a frame the caller fills, raised and of its own
// measure. A simple component on a <div>, in the plain card's look. What is in
// it is its caller's, made on a branch of the caller's own and appended to the
// card's element - a summary, a set of settings, anything. Its inline size is
// the design's, grown by its size; its block size follows its aspect - square
// at 0, −1 the tallest the design allows, 1 the widest; a host may cap it,
// never stretch it; what is inside scrolls beyond it. It is still under the
// hand: a card that does something when pressed is the clickable card.
//
//   var card = new PlainCard(branch.elementMaker("plain-card"), new FixedPlainCard(), me.slot(SLOT_…));
//   card.el.appendChild(new SummaryView(branch.createBranch("summary"), { title: "Grid" }).root);
//   card.el  card.size  card.aspect
// On the style manager (RFC 0071): its module stamped with its header (D19).
// =============================================================================

class PlainCard extends SimpleComponent {
    static TAG = "div";
    static DEGREES = Object.freeze([ExtentSize, ExtentAspect]);
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedPlainCard, place ?? managePlainCard);
        css.addClass(this.el, el_card);   // what is inside fits it, and scrolls beyond it
    }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    /** −1 the tallest the design allows, 0 square, 1 the widest. */
    get aspect() { return this._degree(ExtentAspect); }
    set aspect(a) { this._setDegree(ExtentAspect, a); }
}

