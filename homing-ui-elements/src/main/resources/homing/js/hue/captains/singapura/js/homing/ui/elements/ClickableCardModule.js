// =============================================================================
// ClickableCard — a card that is a button: the plain card's frame which, pressed
// - by the hand, or Enter or Space - does its one thing, fired as its click. It
// lifts under the hand, sinks under the press, takes the focus and shows where
// the page's keys are. A simple component on a <div role="button">, in the
// clickable card's look, made on the branch its owner hands it. Handed the
// page's steward, it joins the keyboard party under that branch's name, or the
// id it is given, and claims by the convention; one inside a pane or a dialog
// is built without, and its holder hands it the keys (key). What is in it is
// its caller's, as in the plain card.
//
//   var card = new ClickableCard(branch.createBranch("open-grid"), { size, aspect, keyboard: steward, keyboardId? });
//   card.setOnClick(handler)     handler.canHandle(EVENT.CLICK) true, handler.handle(event)
//   card.el / card.root  card.size  card.aspect
//   card.key(ev)                 a keydown from whoever holds the keys for it: Enter or Space on the
//                                card is its click; true when taken. No keydown listener of its own
//   card.dispose()               leaves the party, and dissolves its branch
// =============================================================================

const _clickableCardOwner = Object.freeze({ toString: () => "clickableCard" });

class ClickableCard extends SimpleComponent {
    static LEAF = "clickable-card";
    static TAG = "div";
    static EVENTS = Object.freeze([EVENT.CLICK]);
    static DEGREES = Object.freeze(["size", "aspect"]);
    static BEHAVIOR = CardBehavior;

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[ClickableCard] a branch of its own is required");
        var p = Object.assign({}, props || {}), kb = p.keyboard, kbId = p.keyboardId;
        delete p.keyboard;
        delete p.keyboardId;
        branch.activate(_clickableCardOwner);
        super(branch.elementMaker("card"), p, role);
        var self = this;
        css.addClass(this.el, el_card);   // what is inside fits it, and scrolls beyond it
        this.branch = branch;
        this.root = this.el;
        this._kb = null;
        this._kbId = null;
        this._offKeys = null;
        if (kb) {   // the page's steward: a member of the keyboard party, claiming by the convention
            this._kb = kb;
            this._kbId = kb.join(kbId != null ? String(kbId) : branch.name, { keyDown: function (ev) { return self.key(ev); } });
            this._offKeys = Keys.claimOn(this.el, kb, this._kbId, this._look.keys());
        }
    }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    /** −1 the tallest the design allows, 0 square, 1 the widest. */
    get aspect() { return this._degrees.aspect; }
    set aspect(a) { this._degree("aspect", a); }

    /** Pressed: what it does, once and for good. */
    setOnClick(handler) { this._setOn(EVENT.CLICK, handler); }

    /** A keydown from whoever holds the keys for the card: Enter or Space on the card itself is its click; true when taken. */
    key(ev) {
        if (!ev || (ev.key !== "Enter" && ev.key !== " ") || ev.target !== this.el) return false;
        this.el.click();
        return true;
    }

    /** The card leaves the party, and it and everything on its branch go together. */
    dispose() {
        if (this._offKeys) { this._offKeys(); this._offKeys = null; }
        if (this._kb) { this._kb.leave(this._kbId); this._kb = null; }
        try { this.branch.dissolve(); } catch (e) {}
    }
}
