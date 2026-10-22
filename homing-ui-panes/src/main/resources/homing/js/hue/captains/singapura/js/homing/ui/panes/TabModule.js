// =============================================================================
// Tab — one tab on a bar of tabs: its name, the mark of where the keys are, and
// its cross, unless its tab cannot be closed.
// Ported (RFC 0071): the bar that holds it makes it, one for each tab it holds,
// and hears it held - the hand down on it, and up again. Which tab is the one
// shown, and where each sits on the bar, are the bar's (owner, 22 Oct): the
// tab's own states are what it looks like - in use, in the hand, where the
// keys are - each set by the bar, and its hand, which the bar hears. Its cross
// - the house's close button, whether there is one fixed for its life - only
// emits what it manages, its clicks (owner, 22 Oct): the tab passes them on as
// its own ChipCloseControl, and does nothing else - what a close is, the bar
// holding it decides. A press on the cross is no press on the tab.
//
//   var tab = new Tab(branch.createBranch("tab-alpha"), new FixedTab(new FixedCloseButton()), me.place(FixedTab));
//   tab.title = "Alpha"                 its name: its label, its tooltip, and what its cross says it closes
//   tab.onChange(StateHeld, listener)   the hand down on it, then up: press, release
//   tab.onChange(StateChipCloseControl, listener)   its cross pressed: the count up by one
//   tab.selected  tab.dragging  tab.keys   set by the bar
//   tab.held  tab.closes  tab.size  tab.aspect
//
// Its name is a room of its own, in the tab's look; the keys mark, the within
// icon, there while the keys are on it or in it; its cross at its end. A composed
// component on the style manager (RFC 0071): it takes a branch of its own and
// makes its parts on it; its module stamped with its header (D19). Its own
// classes only place.
// =============================================================================

const _tabOwner = Object.freeze({ toString: () => "tab" });

/** Its cross pressed: the tab's own count of the closes asked of it, up by one - and nothing else. */
class TabCross {
    constructor(tab) { this._tab = tab; Object.freeze(this); }
    canHandle(type) { return type === StateClicks; }
    handle() { this._tab._closeAsked(); }
}

class Tab extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        selected: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-selected", on ? "true" : "false"); },
            state: StateSelected
        }),
        keys: Object.freeze({ values: StateKeyboard.states, rest: StateKeyboard.rest, state: StateKeyboard }),
        dragging: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateDragging }),
        held: Object.freeze({ rest: StateHeld.rest, state: StateHeld }),   // the hand down on it, as it has it: no class
        closes: Object.freeze({ rest: 0, state: StateChipCloseControl })   // its cross's clicks, passed on: no class
    });
    static DEGREES = Object.freeze([ExtentSize, ExtentAspect]);
    static BEHAVIOR = TabBehavior;

    /** How much of the colour its mark carries while the cursor is on its bar: part of the way, towards what the tab in use carries at full. */
    static ON_THE_BAR = 0.45;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[Tab] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_tabOwner);
        super(branch.elementMaker("tab"), fixed, FixedTab, place);
        this.branch = branch;
        css.addClass(this.el, mtp_chip);
        this.el.setAttribute("role", "tab");
        // its name: a room of its own, in the tab's look
        this._label = branch.createElement("label", "span");
        this._room(ROOM_LABEL, this._label);
        css.addClass(this._label, mtp_chip_label);
        this.el.appendChild(this._label);
        // the mark of where the keys are, after its name: the within icon, cast as the keys mark - there while the keys are on it or in it
        this._mark = new Icon(branch.elementMaker("mark"), new FixedWithinIcon(), this._place(FixedWithinIcon));
        css.addClass(this._mark.el, mtp_chip_mark);
        this.el.appendChild(this._mark.el);
        // its cross, where its fixed object holds one: the house's close button, cast as what closes it, at its end - taking no keys
        this._cross = null;
        if (this.fixed.close !== null) {
            this._cross = new CloseButton(branch.createBranch("close"), this.fixed.close, this._place(this.fixed.close));
            this._cross.onChange(StateClicks, new TabCross(this));
            css.addClass(this._cross.el, mtp_chip_close);
            this._cross.el.setAttribute("tabindex", "-1");
            this.el.appendChild(this._cross.el);
        }
    }

    /** Its name: its label, its tooltip, and what its cross says it closes. */
    get title() { return this._label.textContent; }
    set title(words) {
        var t = words == null ? "" : String(words);
        this.el.title = t;
        this._label.textContent = t;
        if (this._cross !== null) this._cross.label = "Close " + t;
    }

    /** Whether it has a cross: fixed for its life. */
    get closable() { return this._cross !== null; }

    /** How many times its close was asked, from none: its cross's presses. */
    get closes() { return this._get("closes"); }

    /** The one in use. */
    get selected() { return this._get("selected"); }
    set selected(on) { this._set("selected", !!on); }

    /**
     * Where the keys are, said on the tab: "held" - the cursor on its bar - its mark carrying the colour part of the
     * way; "lent" - the keys inside what it holds - the same mark at full; else none, and no mark.
     */
    get keys() { return this._get("keys"); }
    set keys(at) {
        this._set("keys", at == null ? StateKeyboard.rest : String(at));
        var v = this.keys, on = v === "held" || v === "lent";
        css.toggleClass(this._mark.el, mtp_chip_mark_on, on);
        this._mark.extent = v === "held" ? Tab.ON_THE_BAR : 1;
    }

    /** In the hand: moved along its bar, from the press that grabs it to the release. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    /** The hand on it: down from its press until its release, else up. */
    get held() { return this._get("held"); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }
    get aspect() { return this._degree(ExtentAspect); }
    set aspect(a) { this._setDegree(ExtentAspect, a); }

    /** It and everything on its branch go together. */
    dispose() { this.branch.dissolve(); }

    /** Whether a target is its cross, or in it: a press there is the cross's, no press on the tab. */
    _onCross(target) { return this._cross !== null && !!target && this._cross.el.contains(target); }

    /** Its cross pressed, as its listener hears it: one more close asked. */
    _closeAsked() { this._change("closes", this.closes + 1); }

    /** The hand down on it, as its behaviour hears it. */
    _pressed() { this._change("held", "down"); }

    /** The hand up again. */
    _released() { this._change("held", "up"); }
}
