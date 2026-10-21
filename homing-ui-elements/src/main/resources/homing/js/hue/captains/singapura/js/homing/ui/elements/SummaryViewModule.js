// =============================================================================
// SummaryView — sums one thing up, laid out whole: its title and, beside it, a tag;
// a line of summary; a way to the thing. A view of the house, with no frame of
// its own: shown on whatever holds it - a card, a pane, a dialog. A composed
// component on the style manager (RFC 0071): it takes a branch of its own and
// mints its tree there. Its texts and its link are the house's, each cast as
// what it is to the summary - its title a fourth-level heading, its tag a badge,
// its summary a caption, its way on a link that opens the thing - each placed
// along its slot by its own reference; a part that may be absent is made the
// first time it is given. Its layout - the head's row, the summary below it
// taking the room left and scrolling beyond it, the foot at the bottom, the room
// between them - is its own, the same in every style.
//
//   var s = new SummaryView(branch.createBranch("summary"), new FixedSummaryView(), me.slot(SLOT_…));
//   s.title = "Grid"   s.tag = "new"   s.text = "…"   s.link = { to, label? }
//   s.size = 0.5       the size, live, on the summary and every part it minted
//   s.root             the element its caller places: card.el.appendChild(s.root)
//   s.dispose()        dissolves its branch
// =============================================================================

const _summaryOwner = Object.freeze({ toString: () => "summaryView" });

class SummaryView extends SimpleComponent {
    static TAG = "div";
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[SummaryView] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_summaryOwner);
        super(branch.elementMaker("summary"), fixed, FixedSummaryView, place ?? manageSummaryView);
        this.branch = branch;
        css.addClass(this.el, el_summary);
        this._head = branch.createElement("head", "div");
        css.addClass(this._head, el_summary_head);
        this._title = new Heading4(branch.elementMaker("title"), new FixedHeading4(), this._slot(SLOT_SUMMARY_VIEW_HEADING4_TITLE));
        css.addClass(this._title.el, el_summary_title);
        this._head.appendChild(this._title.el);
        this.el.appendChild(this._head);
        this._tag = null;
        this._text = null;
        this._foot = null;
        this._link = null;
        this.root = this.el;
    }

    /** Its title's words. */
    get title() { return this._title.text; }
    set title(words) { this._title.text = words == null ? "" : String(words); }

    /** Its tag's words: the badge made the first time one is given. */
    get tag() { return this._tag ? this._tag.text : ""; }
    set tag(words) {
        if (words == null || words === "") { if (this._tag) this._tag.text = ""; return; }
        if (!this._tag) {
            this._tag = new Badge(this.branch.elementMaker("tag"), new FixedBadge(), this._slot(SLOT_SUMMARY_VIEW_BADGE_TAG));
            css.addClass(this._tag.el, el_summary_tag);
            this._head.appendChild(this._tag.el);
            this._tag.size = this.size;
        }
        this._tag.text = String(words);
    }

    /** Its line of summary: the caption made the first time one is given. */
    get text() { return this._text ? this._text.text : ""; }
    set text(words) {
        if (words == null || words === "") { if (this._text) this._text.text = ""; return; }
        if (!this._text) {
            this._text = new Caption(this.branch.elementMaker("text"), new FixedCaption(), this._slot(SLOT_SUMMARY_VIEW_CAPTION_SUMMARY));
            css.addClass(this._text.el, el_summary_text);
            this.el.insertBefore(this._text.el, this._foot);
            this._text.size = this.size;
        }
        this._text.text = String(words);
    }

    /** Its way to the thing, { to, label? }: the link made the first time one is given. */
    get link() { return this._link ? Object.freeze({ to: this._link.to, label: this._link.text }) : null; }
    set link(way) {
        if (way == null || way.to == null) return;
        if (!this._link) {
            this._foot = this.branch.createElement("foot", "div");
            css.addClass(this._foot, el_summary_foot);
            this._link = new Link(this.branch.elementMaker("link"), new FixedLink(), this._slot(SLOT_SUMMARY_VIEW_LINK_OPEN));
            this._foot.appendChild(this._link.el);
            this.el.appendChild(this._foot);
            this._link.size = this.size;
            if (this.size !== 0) css.size(this._foot, this.size);
        }
        this._link.text = way.label == null ? String(way.to) : String(way.label);
        this._link.to = way.to;
    }

    /** The size, on the summary, its rows and every part it minted: a size is an element's, not inherited, so the summary carries it to its own. */
    get size() { return this._degree(ExtentSize); }
    set size(s) {
        this._setDegree(ExtentSize, s);
        var v = this._degree(ExtentSize);
        [this._head].concat(this._foot ? [this._foot] : []).forEach(function (el) { css.size(el, v === 0 ? null : v); });
        [this._title, this._tag, this._text, this._link].forEach(function (t) { if (t) t.size = v; });
    }

    /** The summary and everything on its branch go together. */
    dispose() {
        try { this.branch.dissolve(); } catch (e) {}
    }
}
