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
//   var card = new PlainCard(branch.elementMaker("card"), { size: 0, aspect: 0 });
//   card.el.appendChild(new SummaryView(branch.createBranch("summary"), { title: "Grid" }).root);
//   card.el  card.size  card.aspect
// =============================================================================

class PlainCard extends SimpleComponent {
    static LEAF = "plain-card";
    static TAG = "div";
    static DEGREES = Object.freeze(["size", "aspect"]);
    static BEHAVIOR = MarkBehavior;

    constructor(make, props, role) {
        super(make, props, role);
        css.addClass(this.el, el_card);   // what is inside fits it, and scrolls beyond it
    }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    /** −1 the tallest the design allows, 0 square, 1 the widest. */
    get aspect() { return this._degrees.aspect; }
    set aspect(a) { this._degree("aspect", a); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const PlainCardFactory = new SimpleComponentFactory(PlainCard);
