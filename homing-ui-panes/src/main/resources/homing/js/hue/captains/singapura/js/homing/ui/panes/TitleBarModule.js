// =============================================================================
// TitleBar — the head of a pane that floats: what names it - its title, or the
// chip of the one tab it holds - and its close, each where its fixed object
// holds one; where the hand takes the pane. A composed component on the style
// manager (RFC 0071), made by the floating pane it heads. It handles nothing
// (owner, 22 Oct): the hand down on it, anywhere but its close, and up again
// is its Held, which the pane hears and moves itself by; its close's clicks
// are passed on as the pane's FloatCloseControl. In the hand while its pane is
// moved by it - the pane's to say.
//
//   var head = new TitleBar(branch.createBranch("head"), new FixedTitleBar(new FixedLabel(), null, new FixedCloseButton()), me.place(FixedTitleBar));
//   head.title = "Notes"       its title's words, or its chip's name - whichever it holds
//   head.onChange(StateHeld, listener)              the hand down on it, then up
//   head.onChange(StateFloatCloseControl, listener) its close pressed: the count up by one
//   head.dragging  head.held  head.closes
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _titleBarOwner = Object.freeze({ toString: () => "title-bar" });

/** Its close pressed: the head's own count of the closes asked of its pane, up by one - and nothing else. */
class TitleBarClose {
    constructor(head) { this._head = head; Object.freeze(this); }
    canHandle(type) { return type === StateClicks; }
    handle() { this._head._closeAsked(); }
}

class TitleBar extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        dragging: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateDragging }),
        held: Object.freeze({ rest: StateHeld.rest, state: StateHeld }),   // the hand down on it, as it has it: no class
        closes: Object.freeze({ rest: 0, state: StateFloatCloseControl })   // its close's clicks, passed on: no class
    });
    static BEHAVIOR = TitleBarBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[TitleBar] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_titleBarOwner);
        super(branch.elementMaker("head"), fixed, FixedTitleBar, place);   // only ever a part: its pane places it
        this.branch = branch;
        css.addClass(this.el, fp_head);
        // what names it: its title, the house's label cast as a title; or the chip of the one tab its pane holds
        this._title = null;
        this._chip = null;
        if (this.fixed.title !== null) {
            this._title = new Label(branch.elementMaker("title"), this.fixed.title, this._place(this.fixed.title));
            css.addClass(this._title.el, fp_title);
            this.el.appendChild(this._title.el);
        }
        if (this.fixed.chip !== null) {
            this._chip = new Tab(branch.createBranch("chip"), this.fixed.chip, this._place(this.fixed.chip));
            this.el.appendChild(this._chip.el);
        }
        // its close, where it has one: the house's close button, cast as what closes its pane, at its end
        this._close = null;
        if (this.fixed.close !== null) {
            this._close = new CloseButton(branch.createBranch("close"), this.fixed.close, this._place(this.fixed.close));
            this._close.onChange(StateClicks, new TitleBarClose(this));
            this._close.el.setAttribute("tabindex", "-1");
            this.el.appendChild(this._close.el);
        }
        this.root = this.el;
    }

    /** What names its pane: its title's words, or its chip's name. */
    get title() { return this._title !== null ? this._title.text : this._chip !== null ? this._chip.title : ""; }
    set title(words) {
        if (this._title !== null) this._title.text = words;
        if (this._chip !== null) this._chip.title = words;
        if (this._close !== null) this._close.label = "Close " + (words == null ? "" : String(words));
    }

    /** In the hand while its pane is moved by it: the pane's to say. */
    get dragging() { return this._get("dragging"); }
    set dragging(on) { this._set("dragging", !!on); }

    /** The hand on it: down from its press until its release, else up. */
    get held() { return this._get("held"); }

    /** How many times its pane's close was asked, from none: its close's presses. */
    get closes() { return this._get("closes"); }

    /** It and everything on its branch go together. */
    dispose() { this.branch.dissolve(); }

    /** Whether a target is its close, or in it: a press there is the close's, no press on the head. */
    _onClose(target) { return this._close !== null && !!target && this._close.el.contains(target); }

    /** Its close pressed, as its listener hears it: one more close asked of its pane. */
    _closeAsked() { this._change("closes", this.closes + 1); }

    /** The hand down on it, as its behaviour hears it. */
    _pressed() { this._change("held", "down"); }

    /** The hand up again. */
    _released() { this._change("held", "up"); }
}
