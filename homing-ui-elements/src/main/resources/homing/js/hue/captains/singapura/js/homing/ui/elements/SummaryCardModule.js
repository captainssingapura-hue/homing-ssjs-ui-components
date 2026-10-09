// =============================================================================
// SummaryCard — sums one thing up and leads to it: a BRANCH component. It
// takes a sub-branch the caller made for it and mints its own tree on it,
// with its properties. One card per branch.
//
//   var card = new SummaryCard(branch.createBranch("card"), {
//                  title: "Grid", badge: "new", text: "…", link: { to, label },
//                  size: 0, aspect: 0, onClick: fn, keyboard: steward, keyboardId: id });
//     title, badge, text      the head's title and tag, the body's words
//     link: { to, label? }    the foot's link, its address set through the href manager
//     size, aspect            as .size and .aspect below
//     onClick                 an action: the card is a button to the keyboard too
//     keyboard, keyboardId    the page's steward: the card joins the keyboard party
//   the instance:
//       .root            the element the caller appends
//       .body            the bounded region inside; a caller with more than text mints there
//       .size(s)         the size, live, on the card and its parts
//       .aspect(a)       the aspect, live: −1 the tallest the design allows, 0 square, 1 the widest
//       .title(text?)    read, or set
//       .key(ev)         a keydown from whoever holds the keys for it: Enter or Space on a card
//                        with an action is the action; true when taken. A card handed the page's
//                        steward is a member of the keyboard party and claims by the
//                        convention; one inside a pane or a dialog is built without, and its
//                        holder hands it the keys. No keydown listener of its own, ever
//       .dispose()       dissolves the branch
//   The card is a hard frame: its inline size is the design's, grown by its
//   size; its block size follows its aspect — the design's widest to the power
//   of it, square at 0; a host may only cap it; and the body scrolls beyond
//   what the head and the foot leave. It lifts, presses and rings as the design has an
//   interactive thing do; with onClick it is a button to the keyboard too
//   (role, tabindex, Enter and Space); without, nothing happens on a press.
//
// A card's link has its address set through the manager so a typed address
// stays typed. `css` is injected with the styles import; the manager comes
// in by its explicit HrefManager import.
// =============================================================================

const _cardOwner = Object.freeze({ toString: () => "card" });

class SummaryCard {
    constructor(branch, props) {
        if (!branch) throw new Error("[SummaryCard] a branch of its own is required");
        var self = this;
        var p = props || {};
        branch.activate(_cardOwner);
        this.branch = branch;
        this._size = 0;
        this._aspect = 0;
        var card = branch.createElement("card", "article");
        css.addClass(card, el_card);

        var head = branch.createElement("head", "header");
        css.addClass(head, el_card_head);
        // its texts are the house's, each cast as what it is to the card: placed by the card, looking as they are
        this._title = new Heading(HeadingFactory.create(branch, "title"), { text: p.title == null ? "" : String(p.title), level: 2 }, ROLE.TITLE);
        css.addClass(this._title.el, el_card_title);
        head.appendChild(this._title.el);
        var badge = null;
        if (p.badge) {
            badge = new Badge(BadgeFactory.create(branch, "badge"), { text: String(p.badge) }, ROLE.TAG);
            css.addClass(badge.el, el_badge);
            head.appendChild(badge.el);
        }
        card.appendChild(head);

        var body = branch.createElement("body", "div");
        css.addClass(body, el_card_body);
        var text = null;
        if (p.text) {
            text = new Caption(CaptionFactory.create(branch, "text"), { text: String(p.text) }, ROLE.SUMMARY);
            css.addClass(text.el, el_card_text);
            body.appendChild(text.el);
        }
        card.appendChild(body);

        if (p.link && p.link.to) {
            var foot = branch.createElement("foot", "footer");
            css.addClass(foot, el_card_foot);
            var a = branch.createElement("link", "a");
            css.addClass(a, el_card_link);
            HrefManagerInstance.set(a, p.link.to);
            a.textContent = p.link.label == null ? String(p.link.to) : String(p.link.label);
            foot.appendChild(a);
            card.appendChild(foot);
        }

        this._action = typeof p.onClick === "function";
        this._kb = null; this._kbId = null; this._offKeys = null;
        if (this._action) {   // an action: the card is a button to the keyboard too, through the party
            css.addClass(card, el_card_action);
            card.setAttribute("role", "button");
            card.tabIndex = 0;
            card.addEventListener("click", p.onClick);
            if (p.keyboard) {
                var self = this;
                this._kb = p.keyboard;
                this._kbId = this._kb.join(p.keyboardId != null ? String(p.keyboardId) : branch.name, { keyDown: function (ev) { return self.key(ev); } });
                this._offKeys = Keys.claimOn(card, this._kb, this._kbId);
            }
        }

        this.root = card;
        this.body = body;
        this._parts = [card, head].concat(a ? [a] : []);
        this._texts = [this._title].concat(badge ? [badge] : [], text ? [text] : []);
        this.size(p.size == null ? 0 : p.size);
        this.aspect(p.aspect == null ? 0 : p.aspect);
    }

    /** The aspect: on the card alone, since only its frame has a proportion. */
    aspect(a) {
        var n = Math.max(-1, Math.min(1, Number(a)));
        this._aspect = Number.isFinite(n) ? n : 0;
        css.aspect(this.root, this._aspect === 0 ? null : this._aspect);
        return this;
    }

    /** The size, on the card and on every part it minted: the size is an element's, not inherited, so the card carries it to its own. */
    size(s) {
        var n = Math.max(-1, Math.min(1, Number(s)));
        this._size = Number.isFinite(n) ? n : 0;
        var v = this._size === 0 ? null : this._size;
        this._parts.forEach(function (el) { css.size(el, v); });
        var n2 = this._size;
        this._texts.forEach(function (t) { t.size = n2; });   // its texts, by their own size
        return this;
    }

    title(text) {
        if (text !== undefined) this._title.text = String(text);
        return this._title.text;
    }

    /** The card and everything on its branch go together. */
    /** A keydown from whoever holds the keys for the card: Enter or Space on the card is the action; true when taken. */
    key(ev) {
        if (!this._action || !ev || (ev.key !== "Enter" && ev.key !== " ") || ev.target !== this.root) return false;
        this.root.click();
        return true;
    }

    dispose() {
        if (this._offKeys) { this._offKeys(); this._offKeys = null; }
        if (this._kb) { this._kb.leave(this._kbId); this._kb = null; }
        try { this.branch.dissolve(); } catch (e) {}
    }
}

