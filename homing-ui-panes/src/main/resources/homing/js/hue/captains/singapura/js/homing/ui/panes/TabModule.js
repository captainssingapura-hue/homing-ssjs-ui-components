// =============================================================================
// Tab — one tab of a strip: what a pane holds, picked. A simple component on a
// <div role="tab">, in the tab's look, made on the branch its owner hands it -
// a tab-pane's, which makes its one tab once and carries it from strip to
// strip: one tab, whoever holds it. Its parts are the house's: the icon's
// slot (the holder's element - a favicon - or none), its name, the mark of
// where the keys are (the house's icon, the within word, cast as the keys
// mark), and the cross (the house's close button, cast as what closes it),
// unless it is pinned or cannot be closed.
//
// Its states, each held as its class while it holds: in use (Selected,
// aria-selected); where the keys are (Keyboard: "held" while the cursor is on
// its strip - lifted by its look, its mark part of the way - "lent" while what
// it holds has them - down, its mark at full - else "away", data-keys); in
// the hand (Dragging, data-dragging). The pointer over it and the hand down
// on it are the browser's. Its holders say where the keys are and nothing
// else: the lift and the mark are the tab's own.
//
// Its events: Press - the hand down on it, not on its cross: what its holder
// selects it by, the native focus taken away first, since it takes none of
// its own - and Dismiss, its cross pressed. Its menu, a right-click on it, is
// asked of the opener its owner gives it, until the page's steward owns the
// right-click.
//
//   var tab = new Tab(branch.createBranch("chip"), { title, icon?, pinned?, closable? }, ROLE.CHIP);
//   tab.el  tab.title  tab.icon  tab.pinned  tab.closable
//   tab.selected  tab.keys  tab.dragging  tab.size  tab.aspect
//   tab.crosses(target)        whether a target is its cross, or in it
//   tab.menu(opener)           opener.open(at, byKey) → true when a menu opened: its right-click asked of it
//   tab.setOnPress(h)  tab.setOnDismiss(h)
//   tab.setOnSelectedChanged(h)  tab.setOnKeyboardChanged(h)  tab.setOnDraggingChanged(h)
//
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _tabOwner = Object.freeze({ toString: () => "tab" });

/** Its cross pressed: the press kept to the cross, and the tab's Dismiss fired. */
class TabCross {
    constructor(tab) { this._tab = tab; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle(event) { if (event.raw) event.raw.stopPropagation(); this._tab._fire(EVENT.DISMISS, event.raw); }
}

class Tab extends SimpleComponent {
    static LEAF = "tab";
    static TAG = "div";
    static AXES = Object.freeze({
        selected: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-selected", on ? "true" : "false"); },
            state: STATE.SELECTED
        }),
        keys: Object.freeze({
            values: STATE.KEYBOARD.values,
            rest: STATE.KEYBOARD.rest,
            reflect: function (el, at) { if (at === STATE.KEYBOARD.rest) el.removeAttribute("data-keys"); else el.setAttribute("data-keys", at); },
            state: STATE.KEYBOARD
        }),
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("data-dragging", ""); else el.removeAttribute("data-dragging"); },
            state: STATE.DRAGGING
        })
    });
    static EVENTS = Object.freeze([EVENT.PRESS, EVENT.DISMISS]);
    static DEGREES = Object.freeze(["size", "aspect"]);
    static WORDS = Object.freeze(["title"]);
    static PARTS = Object.freeze(["icon"]);
    static BEHAVIOR = TabBehavior;

    /** How much of the colour its mark carries while the cursor is on its strip: part of the way, towards what the tab in use carries at full. */
    static ON_THE_BAR = 0.45;

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[Tab] a branch of its own is required");
        var p = Object.assign({}, props || {}), pinned = !!p.pinned, closable = p.closable !== false && !pinned;
        delete p.pinned;
        delete p.closable;
        branch.activate(_tabOwner);
        super(branch.elementMaker("tab"), p, role);
        css.addClass(this.el, mtp_chip);
        this.el.setAttribute("role", "tab");
        this.pinned = pinned;
        this.closable = closable;
        this._menu = null;
        this._iconEl = null;

        // the icon's slot, always there and shown only when there is an icon: a name on a branch is taken until the
        // branch goes, so a slot made and unmade with its icon could not be made twice
        this._icon = branch.createElement("icon", "span");
        css.addClass(this._icon, mtp_chip_icon);
        this._icon.setAttribute("aria-hidden", "true");
        this.el.appendChild(this._icon);

        this._label = branch.createElement("label", "span");
        css.addClass(this._label, mtp_chip_label);
        this.el.appendChild(this._label);

        // the mark of where the keys are, after its name: the house's icon, the within word, cast as the keys mark;
        // shown only while the keys are on it or in it
        this._mark = new Icon(branch.createElement("mark", Icon.TAG), ICON.WITHIN_ICON, ROLE.KEYS).el;
        css.addClass(this._mark, mtp_chip_mark);
        this.el.appendChild(this._mark);

        // the cross, the house's close button cast as what closes it: its cross and its frame the style's, flat
        this._closer = null;
        this._close = null;
        if (closable) {
            this._closer = new CloseButton(branch.elementMaker("close"), { face: branch.elementMaker("close-face"), label: "Close" }, ROLE.CLOSE);
            this._closer.setOnClick(new TabCross(this));
            this._close = this._closer.el;
            css.addClass(this._close, mtp_chip_close);   // its place at the tab's end; its look its own
            this._close.setAttribute("tabindex", "-1");
            this.el.appendChild(this._close);
        }
        this.title = p.title;   // its name again, now that its parts are made
        this.icon = p.icon || null;
    }

    /** Its name: its label, its tooltip, and what its cross says it closes. */
    get title() { return this._label ? this._label.textContent : ""; }
    set title(words) {
        if (!this._label) return;
        var t = words == null ? "" : String(words);
        this.el.title = t;
        this._label.textContent = t;
        if (this._closer) this._closer.label = "Close " + t;
    }

    /** The holder's element in its icon's slot, or none. */
    get icon() { return this._iconEl; }
    set icon(el) {
        if (!this._icon) return;
        while (this._icon.firstChild) this._icon.removeChild(this._icon.firstChild);
        this._iconEl = el || null;
        if (this._iconEl) this._icon.appendChild(this._iconEl);
        css.toggleClass(this._icon, mtp_chip_icon_on, !!this._iconEl);
    }

    /** In use. */
    get selected() { return this._get("selected"); }
    set selected(on) { this._set("selected", !!on); }

    /**
     * Where the keys are, said on the tab: "held" - the cursor on its strip, the arrows walking the tabs - lifted by
     * its look, its mark carrying the colour part of the way; "lent" - the keys inside what it holds - down, the same
     * mark at full; else "away", and no mark.
     */
    get keys() { return this._get("keys"); }
    set keys(at) {
        var v = at == null ? STATE.KEYBOARD.rest : String(at);
        this._set("keys", v);
        var on = v === "held" || v === "lent";
        css.toggleClass(this._mark, mtp_chip_mark_on, on);
        css.extent(this._mark, on ? (v === "held" ? Tab.ON_THE_BAR : 1) : null);
    }

    /** In the hand: from the press that grabs it to the release. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }
    get aspect() { return this._degrees.aspect; }
    set aspect(a) { this._degree("aspect", a); }

    /** Whether a target is its cross, or inside it. */
    crosses(target) { return !!this._close && !!target && this._close.contains(target); }

    /** The opener its right-click is asked of: opener.open(at, byKey) → true when a menu opened, and only then is the browser's own suppressed. */
    menu(opener) { this._menu = opener || null; return this; }

    /** The hand down on it, not on its cross: what its holder selects it by. */
    setOnPress(handler) { this._setOn(EVENT.PRESS, handler); }
    /** Its cross pressed. */
    setOnDismiss(handler) { this._setOn(EVENT.DISMISS, handler); }
    setOnSelectedChanged(handler) { this._setOn(STATE.SELECTED.changed, handler); }
    setOnKeyboardChanged(handler) { this._setOn(STATE.KEYBOARD.changed, handler); }
    setOnDraggingChanged(handler) { this._setOn(STATE.DRAGGING.changed, handler); }
}
