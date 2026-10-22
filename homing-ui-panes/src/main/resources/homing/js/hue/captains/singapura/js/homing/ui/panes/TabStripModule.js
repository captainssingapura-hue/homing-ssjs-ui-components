// =============================================================================
// TabStrip — the bar over a multi-tab pane. Ported (RFC 0071) a step at a time:
// for now its one plus alone, the way to ask for a tab - a regular button, the
// house's icon button, its face the add icon its fixed object holds. It handles
// nothing (owner, 22 Oct): a press on its plus is passed on, its own count of
// the adds asked of it - TabAddControl - going up, for its owner to hear. Its
// chips, its count, the drag that reorders come as they are ported.
//
//   var strip = new TabStrip(branch.createBranch("bar"), new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), me.place(FixedTabStrip));
//   strip.onChange(StateTabAddControl, listener)   its plus pressed: the count up by one
//   strip.adds      how many adds were asked of it, from none
//   strip.current   lit while its pane is the one being worked in
// A composed component on the style manager (RFC 0071): it takes a branch of its
// own and makes its parts on it; its module stamped with its header (D19).
// =============================================================================

const _tabStripOwner = Object.freeze({ toString: () => "tab-strip" });

/** Its plus pressed: the strip's own count of the adds asked of it, up by one. */
class TabStripAdd {
    constructor(strip) { this._strip = strip; Object.freeze(this); }
    canHandle(type) { return type === StateClicks; }
    handle() { this._strip._asked(); }
}

class TabStrip extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: StateCurrent
        }),
        adds: Object.freeze({ rest: 0, state: StateTabAddControl })   // its plus's presses, passed on: no class
    });
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[TabStrip] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_tabStripOwner);
        super(branch.elementMaker("strip"), fixed, FixedTabStrip, place ?? manageTabStrip);
        this.branch = branch;
        css.addClass(this.el, mtp_strip);
        this.el.setAttribute("role", "tablist");
        // its one plus: an icon button, its face the icon its fixed object holds - the add icon
        var plus = this.fixed.voidRole;
        this._plus = new IconButton(branch.createBranch("plus"), plus, this._place(plus));
        this._plus.label = "Add a tab";
        css.addClass(this._plus.el, mtp_rail_add);
        this._plus.onChange(StateClicks, new TabStripAdd(this));
        this.el.appendChild(this._plus.el);
        this.root = this.el;
    }

    /** How many adds were asked of it, from none: its plus's presses. */
    get adds() { return this._get("adds"); }

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** It and everything on its branch go together. */
    dispose() { this.branch.dissolve(); }

    /** Its plus pressed, as its listener hears it: one more add asked. */
    _asked() { this._change("adds", this.adds + 1); }
}
