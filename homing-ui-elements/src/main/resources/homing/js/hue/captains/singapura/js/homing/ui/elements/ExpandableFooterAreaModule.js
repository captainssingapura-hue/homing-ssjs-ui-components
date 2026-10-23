// =============================================================================
// ExpandableFooterArea — a status footer at the foot, and above it an area that
// opens over what it covers (owner, 23 Oct): the status footer tells the state
// of things, read-only; the area holds its caller's control widget, as a dialog
// would, to act on them. A container only: both widgets are its caller's.
//
// It opens when the pointer has rested on it a moment, or on a press on its
// status footer. It stays open while anything keeps it so - the pointer on it,
// on its footer or its area; its control widget active (in use, as the widget
// says: a field being typed in, a drag going on - every widget says whether it
// is active, isActive(), and tells when that changes, onActive(listener)); or
// its owner holding it - and closes by itself a moment after nothing does.
// Nothing closes it explicitly. The foot's own: each side a component of its own.
//
//   var f = new ExpandableFooterArea(branch.createBranch("log"), new FixedExpandableFooterArea(), place?);
//   f.status.mount(root, party?)   the status widget, on its footer
//   f.mount(widget)   the control widget in its area: { root, party?, isActive(), onActive(listener) }   f.unmount()
//   f.hold(on)   f.held   f.expanded   f.onChange(StateExpanded, listener)
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _footerAreaOwner = Object.freeze({ toString: () => "expandableFooterArea" });

/** Its control widget's activity, as it hears it: whether anything still keeps it open, looked at again. */
class WidgetActivity {
    constructor(area, widget) { this.area = area; this.widget = widget; Object.freeze(this); }
    handle(active) { if (this.area._widget === this.widget) this.area._review(); }
}

class ExpandableFooterArea extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        expanded: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateExpanded })   // what keeps it open decides: no setter
    });
    static BEHAVIOR = ExpandableFooterAreaBehavior;
    /** How long the pointer rests on it before it opens, and how long after the last reason before it closes. */
    static OPEN_MS = 150;   // quick to answer the pointer (owner, 23 Oct): a rest, not a wait
    static CLOSE_MS = 400;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[ExpandableFooterArea] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_footerAreaOwner);
        super(branch.elementMaker("area-footer"), fixed, FixedExpandableFooterArea, place ?? manageExpandableFooterArea);
        this.branch = branch;
        css.addClass(this.el, el_footer_area);
        this._area = branch.createElement("area", "div");
        this._room(ROOM_AREA, this._area);
        css.addClass(this._area, el_footer_area_panel);
        css.addClass(this._area, el_footer_area_closed);
        this.status = new StatusFooter(branch.createBranch("status"), new FixedStatusFooter(), this._place(FixedStatusFooter, RoleFoot));
        this.el.append(this._area, this.status.el);
        this._widget = null;
        this._grafted = false;
        this._pointer = false;
        this._held = false;
        this._timer = null;
        this.root = this.el;
    }

    /** The control widget, mounted by its caller in its area: its root, its branch of the page grafted, its activity heard. One at a time. */
    mount(widget) {
        if (this._widget !== null) throw new Error("[ExpandableFooterArea] shows a widget already: unmount it first");
        if (!widget || !widget.root || widget.root.nodeType !== 1) throw new TypeError("[ExpandableFooterArea] what it shows is a widget: its root an element");
        if (typeof widget.isActive !== "function" || typeof widget.onActive !== "function")
            throw new TypeError("[ExpandableFooterArea] a widget says whether it is active - isActive() - and tells when that changes - onActive(listener)");
        if (widget.party != null) { this.branch.graft("area", widget.party); this._grafted = true; }
        this._widget = widget;
        this._area.appendChild(widget.root);
        widget.onActive(new WidgetActivity(this, widget));
        this._review();
    }

    /** The control widget let go: still its caller's. */
    unmount() {
        var w = this._widget;
        if (w === null) return;
        this._widget = null;
        this._area.removeChild(w.root);
        if (this._grafted) this.branch.detach("area");
        this._grafted = false;
        this._review();
    }

    /** Held open by its owner - something on it a person must see - or let go: it closes once nothing else keeps it. */
    hold(on) { this._held = !!on; this._review(); }
    get held() { return this._held; }

    /** Its area open, or not. */
    get expanded() { return this._get("expanded"); }

    /** It and everything on its branch go together; what it shows is its caller's: let go, first. */
    dispose() { this._wait(null, null); this.unmount(); this.status.dispose(); this.branch.dissolve(); }

    // ── as its behaviour tells it ─────────────────────────────────────────
    _pointerOn() { this._pointer = true; if (!this.expanded) this._wait(ExpandableFooterArea.OPEN_MS, () => this._open()); else this._wait(null, null); }
    _pointerOff() { this._pointer = false; this._review(); }
    _pressed() { this._wait(null, null); this._open(); }

    /** Whether anything keeps it open: the pointer on it, its widget active, its owner. */
    _kept() { return this._pointer || this._held || (this._widget !== null && this._widget.isActive() === true); }

    /** What keeps it open, looked at again: held, open at once; else, open and kept, it stays; open and not kept, it closes a moment after. */
    _review() {
        if (this._held) { this._wait(null, null); this._open(); return; }
        if (!this.expanded) { if (!this._pointer) this._wait(null, null); return; }
        if (this._kept()) this._wait(null, null);
        else this._wait(ExpandableFooterArea.CLOSE_MS, () => { if (!this._kept()) this._close(); });
    }

    _wait(ms, then) {
        if (this._timer !== null) { clearTimeout(this._timer); this._timer = null; }
        if (then) this._timer = setTimeout(() => { this._timer = null; then(); }, ms);
    }
    _open() { if (this.expanded) return; css.removeClass(this._area, el_footer_area_closed); this._set("expanded", true); }
    _close() { if (!this.expanded) return; css.addClass(this._area, el_footer_area_closed); this._set("expanded", false); }
}
