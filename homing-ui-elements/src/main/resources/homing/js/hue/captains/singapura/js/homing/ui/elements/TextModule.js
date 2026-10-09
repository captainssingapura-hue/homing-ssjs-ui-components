// =============================================================================
// Text — what the text leaves share, as declarations: words, at a size, and
// nothing to do (TextBehavior). A simple component (SimpleComponent); never
// made on its own - a text is one of its leaves, made on the element its
// leaf's companion makes, and wears its leaf's look in its role (Look):
//
//   var h = new Heading(HeadingFactory.create(branch, "title"), { text: "Books", level: 2 }, ROLE.TITLE);
//   h.text = "Shelves"   the words, its whole face
//   h.size = 0.5         −1 the smallest … 1 the biggest; 0 regular
//
// Its element keeps no margin of its own (el_text): where it sits, and the room
// round it, are its owner's to give.
// =============================================================================

class Text extends SimpleComponent {
    static DEGREES = Object.freeze(["size"]);
    static WORDS = Object.freeze(["text"]);
    static BEHAVIOR = TextBehavior;

    constructor(el, props, role) {
        super(el, props, role);
        css.addClass(el, el_text);
    }

    get text() { return this.el.textContent; }
    set text(words) { this._look.text(this.el, words == null ? "" : String(words)); }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }
}
