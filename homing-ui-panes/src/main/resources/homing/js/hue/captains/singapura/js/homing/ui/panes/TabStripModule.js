// =============================================================================
// TabStrip — the bar over a multi-tab pane: a chip for each tab it holds, then
// its one plus. Ported (RFC 0071) a step at a time. The bar owns its chips
// (owner, 22 Oct): it makes them, and every move of theirs is its own - a press
// on a chip makes it the one in use; held and moved along the bar, the chip is
// in the hand and the others make way, and let go it stays where it is. Its
// chips' states are its business alone: what its owner hears are the bar's own
// changes - which tab is in use (ActiveTab), the order of its tabs (TabOrder),
// told once a drag ends, if it changed anything - and the adds asked of it, its
// plus pressed (TabAddControl), which it handles none of: what is added is its
// owner's caller's to decide. Its count and its landing come as they are ported.
//
//   var strip = new TabStrip(branch.createBranch("bar"), new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), me.place(FixedTabStrip));
//   strip.hold([{ id: "alpha", title: "Alpha" }, ...])   the tabs it holds, once: the first in use
//   strip.onChange(StateActiveTab, listener)   another tab in use: its id before and after
//   strip.onChange(StateTabOrder, listener)    its tabs in another order: before and after
//   strip.onChange(StateTabAddControl, listener)   its plus pressed: the count up by one
//   strip.active  strip.order  strip.adds  strip.current
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

/** One of its chips held, or let go: told to the strip, by the chip's tab id. */
class TabStripHeld {
    constructor(strip, id) { this._strip = strip; this._id = id; Object.freeze(this); }
    canHandle(type) { return type === StateHeld; }
    handle(change) { if (change.after === "down") this._strip._pressed(this._id); else this._strip._released(this._id); }
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
        adds: Object.freeze({ rest: 0, state: StateTabAddControl }),   // its plus's presses, passed on: no class
        active: Object.freeze({ rest: StateActiveTab.rest, state: StateActiveTab }),   // the tab in use, by its id: no class
        order: Object.freeze({ rest: StateTabOrder.rest, state: StateTabOrder })   // its tabs' ids, in order: no class
    });
    static BEHAVIOR = TabStripBehavior;

    /** How far the hand moves a held chip before it is in the hand, in pixels: a press that wanders less is a press. */
    static DRAG_FROM = 4;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[TabStrip] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_tabStripOwner);
        super(branch.elementMaker("strip"), fixed, FixedTabStrip, place ?? manageTabStrip);
        this.branch = branch;
        css.addClass(this.el, mtp_strip);
        this.el.setAttribute("role", "tablist");
        this._chips = new Map();   // its tabs' ids to their chips: its own
        this._row = [];   // its tabs' ids as its chips stand now, a drag moving them
        this._grabbed = null;   // the id of the chip held, or none
        // its one plus: an icon button, its face the icon its fixed object holds - the add icon
        var plus = this.fixed.voidRole;
        this._plus = new IconButton(branch.createBranch("plus"), plus, this._place(plus));
        this._plus.label = "Add a tab";
        css.addClass(this._plus.el, mtp_rail_add);
        this._plus.onChange(StateClicks, new TabStripAdd(this));
        this.el.appendChild(this._plus.el);
        this.root = this.el;
    }

    /**
     * The tabs it holds, given once, in order - each an id, a name of the tab's own, and its title: a chip made for
     * each, before its plus, and the first in use. Taking tabs in and out comes with its own step.
     */
    hold(tabs) {
        if (this._chips.size > 0) throw new Error("[TabStrip] holds its tabs already: taking tabs in and out comes with its own step");
        if (!Array.isArray(tabs) || tabs.length === 0) throw new TypeError("[TabStrip] holds tabs: a list of { id, title }, one at least");
        var ids = tabs.map(function (t) { return t && t.id; });
        if (!StateTabOrder.allows(ids)) throw new TypeError("[TabStrip] each tab's id a name, each once - not " + ids.join(", "));
        for (var t of tabs) {
            var tab = new Tab(this.branch.createBranch("tab-" + t.id), new FixedTab(null), this._place(FixedTab));
            tab.title = t.title;
            tab.onChange(StateHeld, new TabStripHeld(this, t.id));
            this.el.insertBefore(tab.el, this._plus.el);
            this._chips.set(t.id, tab);
        }
        this._row = ids.slice();
        this._change("order", ids);
        this._select(ids[0]);
    }

    /** The tab in use, by its id; none before it holds any. */
    get active() { return this._get("active"); }

    /** Its tabs' ids, in the order they stand on it. */
    get order() { return this._get("order"); }

    /** How many adds were asked of it, from none: its plus's presses. */
    get adds() { return this._get("adds"); }

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** It and everything on its branch go together. */
    dispose() { this._behavior.drop(); this.branch.dissolve(); }

    /** Its plus pressed, as its listener hears it: one more add asked. */
    _asked() { this._change("adds", this.adds + 1); }

    /** A chip held: made the one in use, and grabbed - the hand's moves heard until it lets go. */
    _pressed(id) {
        this._select(id);
        this._grabbed = id;
        this._behavior.grab();
    }

    /** A chip let go: out of the hand where it stands, and its order told if the drag changed it. */
    _released(id) {
        if (this._grabbed !== id) return;
        this._grabbed = null;
        this._behavior.drop();
        this._chips.get(id).dragging = false;
        this._change("order", this._row);
    }

    /**
     * The hand moved, a chip held: past its start, the chip is in the hand; and it stands among the others where the
     * hand is - after each whose middle the hand has passed.
     */
    _dragTo(x, moved) {
        var id = this._grabbed;
        if (id === null) return;
        var chip = this._chips.get(id), chips = this._chips;
        if (!chip.dragging) {
            if (Math.abs(moved) < TabStrip.DRAG_FROM) return;
            chip.dragging = true;
        }
        var others = this._row.filter(function (o) { return o !== id; });
        var at = others.filter(function (o) { var r = chips.get(o).el.getBoundingClientRect(); return x > r.left + r.width / 2; }).length;
        if (at === this._row.indexOf(id)) return;
        others.splice(at, 0, id);
        this._row = others;
        this.el.insertBefore(chip.el, at + 1 < others.length ? chips.get(others[at + 1]).el : this._plus.el);
    }

    /** A tab made the one in use: its chip selected, every other not. */
    _select(id) {
        this._chips.forEach(function (tab, t) { tab.selected = t === id; });
        this._change("active", id);
    }
}
