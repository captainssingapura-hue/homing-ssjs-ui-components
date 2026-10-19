// =============================================================================
// Text — what the text leaves share, as declarations: a simple component
// (SimpleComponent) whose face is words - its text - at a size, with nothing
// to do. On the style manager (RFC 0071): never made on its own - a text is
// one of its leaves, each with the constructor every component has, its own
// fixed type and its own binding handed on. Its module is stamped with every
// leaf's header (D19), so the size it varies by is the extents' dual's
// (ExtentSize); its element wears the provided layout class el_text, the
// style-agnostic manager's, which no style ever changes.
//
//   var k = new Kicker(branch.elementMaker("kicker"), new FixedKicker(), me.slot(SLOT_…));
//   k.text = "Settings"     the words
//   k.size = -1             −1 the smallest … 1 the biggest; 0 regular, the rest
// =============================================================================

class Text extends SimpleComponent {
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = TextBehavior;

    constructor(make, fixed, Fixed, place) {
        super(make, fixed, Fixed, place);
        css.addClass(this.el, el_text);   // laid out as words: the style-agnostic manager's
    }

    get text() { return this.el.textContent; }
    set text(words) { this.el.textContent = words == null ? "" : String(words); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }
}
