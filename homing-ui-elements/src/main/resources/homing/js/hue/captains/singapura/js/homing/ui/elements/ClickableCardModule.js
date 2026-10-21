// =============================================================================
// ClickableCard — a card that is a button: the plain card's frame which, pressed
// - by the hand, or Enter or Space - does its one thing, fired as its click. It
// lifts under the hand, sinks under the press, takes the focus and shows where
// the page's keys are. A composed component on the style manager (RFC 0071): it
// takes a branch of its own and makes its <div role="button"> on it. What is in
// it is its caller's, as in the plain card.
//
// Handed the page's steward - joinKeys(steward, id?), once - it joins the
// keyboard party under that branch's name, or the id it is given, and claims by
// the convention. Where the keys are on it is told it by the steward,
// marked(state), and it sets its own Keyboard state - held, lent, candidate or
// away - through its own reference (D16): nothing is written on it by anyone
// else. One inside a pane or a dialog joins no steward, and its holder hands it
// the keys (key).
//
//   var card = new ClickableCard(branch.createBranch("open-grid"), new FixedClickableCard(), me.place(FixedClickableCard));
//   card.joinKeys(steward, id?)   a member of the keyboard party, once
//   card.setOnClick(handler)      handler.canHandle(EventClick) true, handler.handle(event)
//   card.setOnKeyboardChanged(handler)   where the keys are on it changed, as the steward told it
//   card.el / card.root  card.size  card.aspect  card.keys
//   card.key(ev)                  a keydown from whoever holds the keys for it: Enter or Space on the
//                                 card is its click; true when taken. No keydown listener of its own
//   card.dispose()                leaves the party, and dissolves its branch
// =============================================================================

const _clickableCardOwner = Object.freeze({ toString: () => "clickableCard" });

class ClickableCard extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        keys: Object.freeze({
            values: StateKeyboard.states,
            rest: StateKeyboard.rest,
            state: StateKeyboard
        })
    });
    static EVENTS = Object.freeze([EventClick]);
    static DEGREES = Object.freeze([ExtentSize, ExtentAspect]);
    static BEHAVIOR = CardBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[ClickableCard] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_clickableCardOwner);
        super(branch.elementMaker("card"), fixed, FixedClickableCard, place ?? manageClickableCard);
        css.addClass(this.el, el_card);   // what is inside fits it, and scrolls beyond it
        this.branch = branch;
        this.root = this.el;
        this._kb = null;
        this._kbId = null;
        this._offKeys = null;
    }

    /** A member of the page's keyboard party, once: under the branch's name, or the id given; claiming by the convention. */
    joinKeys(steward, id) {
        if (this._kb) throw new Error("[ClickableCard] a member of the keyboard party already: joined once, it stays");
        if (!steward || typeof steward.join !== "function") throw new Error("[ClickableCard] joins the page's KeyboardSteward");
        var self = this;
        this._kb = steward;
        this._kbId = steward.join(id != null ? String(id) : this.branch.name, {
            keyDown: function (ev) { return self.key(ev); },
            marked: function (at) { self._change("keys", at, null); }   // where the keys are on it, as the steward says: its own state
        });
        this._offKeys = Keys.claimOn(this.el, steward, this._kbId);
        return this;
    }

    /** Where the page's keys are on it: away, candidate, lent or held - the steward's to say, its own to set. */
    get keys() { return this._get("keys"); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    /** −1 the tallest the design allows, 0 square, 1 the widest. */
    get aspect() { return this._degree(ExtentAspect); }
    set aspect(a) { this._setDegree(ExtentAspect, a); }

    /** Pressed: what it does, once and for good. */
    setOnClick(handler) { this._setOn(EventClick, handler); }

    /** Where the keys are on it changed, as the steward told it. */
    setOnKeyboardChanged(handler) { this._setOn(StateKeyboard, handler); }

    /** A press, as its behaviour hears it: its click. */
    _clicked(ev) { this._fire(EventClick, ev); }

    /**
     * A keydown from whoever holds the keys for the card: Enter or Space is its click; true when taken. One aimed at a
     * control inside it is that control's. With the card holding the keys and nothing natively focused - its own root
     * the steward never lets the browser focus - a key comes to it aimed at the page itself.
     */
    key(ev) {
        if (!ev || (ev.key !== "Enter" && ev.key !== " ")) return false;
        if (ev.target !== this.el && ev.target && this.el.contains(ev.target)) return false;
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
