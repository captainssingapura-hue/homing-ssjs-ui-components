// =============================================================================
// SummaryView — sums one thing up, laid out whole: its title and, beside it, a tag;
// a line of summary; a way to the thing. A view of the house, with no frame of
// its own: shown on whatever holds it - a card, a pane, a dialog. A BRANCH
// component: it takes a sub-branch its caller made for it and mints its tree
// there. Its texts and its link are the house's, each cast as what it is to the
// summary - its title a fourth-level heading, its tag a badge, its summary a
// caption, its way on a link that opens the thing, its address set through the
// href manager, so a typed address stays typed. Its layout - the head's row,
// the summary below it taking the room left and scrolling beyond it, the foot
// at the bottom, the room between them - is its own, the same in every style.
//
//   var s = new SummaryView(branch.createBranch("summary"), {
//               title: "Grid", tag: "new", text: "…", link: { to, label? }, size: 0 });
//   s.root            the element its caller places: card.el.appendChild(s.root)
//   s.size(n)         the size, live, on the summary and every part it minted
//   s.title(text?)    read, or set
//   s.dispose()       dissolves its branch
// =============================================================================

const _summaryOwner = Object.freeze({ toString: () => "summaryView" });

class SummaryView {
    static LEAF = "summary-view";

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[SummaryView] a branch of its own is required");
        var p = props || {};
        for (var k in p) if (["title", "tag", "text", "link", "size"].indexOf(k) < 0) throw new Error("[SummaryView] has no property '" + k + "'");
        branch.activate(_summaryOwner);
        this.branch = branch;
        var root = branch.createElement("summary", "div");
        css.addClass(root, el_summary);
        new Look(SummaryView, role == null ? ROLE.STANDALONE : role).wear(root);

        var head = branch.createElement("head", "div");
        css.addClass(head, el_summary_head);
        this._title = new Heading4(branch.elementMaker("title"), { text: p.title == null ? "" : String(p.title) }, ROLE.TITLE);
        css.addClass(this._title.el, el_summary_title);
        head.appendChild(this._title.el);
        var tag = null;
        if (p.tag) {
            tag = new Badge(branch.elementMaker("tag"), { text: String(p.tag) }, ROLE.TAG);
            css.addClass(tag.el, el_summary_tag);
            head.appendChild(tag.el);
        }
        root.appendChild(head);

        var text = null;
        if (p.text) {
            text = new Caption(branch.elementMaker("text"), { text: String(p.text) }, ROLE.SUMMARY);
            css.addClass(text.el, el_summary_text);
            root.appendChild(text.el);
        }

        var foot = null, link = null;
        if (p.link && p.link.to) {
            foot = branch.createElement("foot", "div");
            css.addClass(foot, el_summary_foot);
            link = new Link(branch.elementMaker("link"), { text: p.link.label == null ? String(p.link.to) : String(p.link.label), to: p.link.to }, ROLE.OPEN);
            foot.appendChild(link.el);
            root.appendChild(foot);
        }

        this.root = root;
        this._rows = [root, head].concat(foot ? [foot] : []);   // its room between its parts, by its size
        this._texts = [this._title].concat(tag ? [tag] : [], text ? [text] : [], link ? [link] : []);
        this.size(p.size == null ? 0 : p.size);
    }

    /** The size, on the summary, its rows and every part it minted: a size is an element's, not inherited, so the summary carries it to its own. */
    size(s) {
        var n = Math.max(-1, Math.min(1, Number(s)));
        var v = Number.isFinite(n) ? n : 0;
        this._rows.forEach(function (el) { css.size(el, v === 0 ? null : v); });
        this._texts.forEach(function (t) { t.size = v; });
        return this;
    }

    title(text) {
        if (text !== undefined) this._title.text = String(text);
        return this._title.text;
    }

    /** The summary and everything on its branch go together. */
    dispose() {
        try { this.branch.dissolve(); } catch (e) {}
    }
}
