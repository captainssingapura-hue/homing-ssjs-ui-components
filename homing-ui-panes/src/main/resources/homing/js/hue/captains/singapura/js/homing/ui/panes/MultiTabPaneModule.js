// =============================================================================
// MultiTabPane — a pane of tabs: its bar over its slot, where what each tab
// shows is mounted, one at a time - the tab in use's. Ported (RFC 0071) a step
// at a time. It manages no tab in or out (owner, 22 Oct): its caller does. The
// tabs it holds are given once, for now - taking tabs in and out comes with its
// own step - and for each its caller mounts what it shows: an element, which
// goes with its tab wherever the tab is moved along the bar. The bar is its
// own, and so is all that goes on on it - which tab is in use, the chips held
// and moved: what its caller hears is the one change that matters to it, the
// order of its tabs (TabOrder), told once a drag ends, if it changed it. A
// press on its bar's plus is passed on - its own TabAddControl going up - and
// what is added, if anything, is its caller's to decide. Its keys come as they
// are ported.
//
//   var pane = new MultiTabPane(branch.createBranch("pane"),
//                  new FixedMultiTabPane(new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), null), me.place(FixedMultiTabPane));
//   pane.hold([{ id: "alpha", title: "Alpha" }, ...])   the tabs it holds, once: the first in use
//   pane.mount("alpha", element)   what a tab shows, its caller's: in the slot while its tab is in use
//   pane.onChange(StateTabOrder, listener)   its tabs in another order: before and after
//   pane.onChange(StateTabAddControl, listener)   its bar's plus pressed: the count up by one
//   pane.order     its tabs' ids, in the order they stand
//   pane.active    the tab in use, by its id
//   pane.adds      how many adds were asked of it, from none
//   pane.keys      where the page's keys are on it - the steward's to say
//   pane.note      its note's words, where it was made with a note
//   pane.root      its element; pane.dispose()
// A composed component on the style manager (RFC 0071): it takes a branch of its
// own and makes its parts on it; its module stamped with its header (D19).
// =============================================================================

const _multiTabPaneOwner = Object.freeze({ toString: () => "multi-tab-pane" });

/** Its bar's plus pressed: the pane's own count of the adds asked of it, up by one. */
class MultiTabPaneAdd {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateTabAddControl; }
    handle() { this._pane._asked(); }
}

/** Another tab in use on its bar: what that tab shows put in its slot. */
class MultiTabPaneShow {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateActiveTab; }
    handle(change) { this._pane._show(change.after); }
}

/** Its bar's tabs in another order: its own order, told to its caller. */
class MultiTabPaneOrder {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateTabOrder; }
    handle(change) { this._pane._ordered(change.after); }
}

class MultiTabPane extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        keys: Object.freeze({ values: StateKeyboard.states, rest: StateKeyboard.rest, state: StateKeyboard }),
        adds: Object.freeze({ rest: 0, state: StateTabAddControl }),   // its bar's adds, passed on: no class
        order: Object.freeze({ rest: StateTabOrder.rest, state: StateTabOrder })   // its bar's order, passed on: no class
    });
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[MultiTabPane] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_multiTabPaneOwner);
        super(branch.elementMaker("pane"), fixed, FixedMultiTabPane, place ?? manageMultiTabPane);
        this.branch = branch;
        css.addClass(this.el, mtp_pane);
        this._shows = new Map();   // its tabs' ids to what each shows, its caller's - none until mounted
        // its bar, the tab strip its fixed object holds: which tab is in use heard, its order and what it asks for passed on, a level up
        this._strip = new TabStrip(branch.createBranch("strip"), this.fixed.tabs, this._place(this.fixed.tabs));
        this._strip.onChange(StateTabAddControl, new MultiTabPaneAdd(this));
        this._strip.onChange(StateActiveTab, new MultiTabPaneShow(this));
        this._strip.onChange(StateTabOrder, new MultiTabPaneOrder(this));
        this.el.appendChild(this._strip.el);
        // its slot: its own element, where what the tab in use shows is - empty until its caller mounts it
        this._slot = branch.createElement("slot", "div");
        this._room(ROOM_SLOT, this._slot);
        css.addClass(this._slot, mtp_content);
        css.addClass(this._slot, mtp_empty);
        this.el.appendChild(this._slot);
        this._note = null;
        if (this.fixed.note !== null) {   // its note, the house's caption cast as a note: under its slot
            this._note = new Caption(branch.elementMaker("note"), this.fixed.note, this._place(this.fixed.note));
            this.el.appendChild(this._note.el);
        }
        this.root = this.el;
    }

    /** The tabs it holds, given once, in order - each an id and its title: its bar's chips, the first in use, its slot empty for each until mounted. */
    hold(tabs) {
        if (this._shows.size > 0) throw new Error("[MultiTabPane] holds its tabs already: taking tabs in and out comes with its own step");
        if (Array.isArray(tabs)) for (var t of tabs) if (t && typeof t.id === "string") this._shows.set(t.id, null);
        try { this._strip.hold(tabs); } catch (e) { this._shows.clear(); throw e; }
    }

    /** What one of its tabs shows, its caller's element: in its slot while that tab is in use. Once a tab. */
    mount(id, element) {
        if (!this._shows.has(id)) throw new Error("[MultiTabPane] holds no tab " + id);
        if (this._shows.get(id) !== null) throw new Error("[MultiTabPane] shows something for " + id + " already");
        if (!element || element.nodeType !== 1) throw new TypeError("[MultiTabPane] what a tab shows is an element");
        this._shows.set(id, element);
        if (id === this.active) this._show(id);
    }

    /** Its tabs' ids, in the order they stand on its bar. */
    get order() { return this._get("order"); }

    /** The tab in use on its bar, by its id. */
    get active() { return this._strip.active; }

    /** How many adds were asked of it, from none: its bar's plus pressed. */
    get adds() { return this._get("adds"); }

    /** Where the page's keys are on it: away, candidate, lent or held - the steward's to say. */
    get keys() { return this._get("keys"); }

    /** Its note's words, where it was made with a note. */
    get note() { return this._note ? this._note.text : ""; }
    set note(words) {
        if (!this._note) throw new TypeError("[MultiTabPane] made with no note: whether it has one is fixed for its life");
        this._note.text = words == null ? "" : String(words);
    }

    /** It and everything on its branch go together; what its tabs show is its caller's. */
    dispose() { this._strip.dispose(); this.branch.dissolve(); }

    /** Its bar's plus pressed, as its listener hears it: one more add asked. */
    _asked() { this._change("adds", this.adds + 1); }

    /** Its bar's tabs in another order, as its listener hears it. */
    _ordered(order) { this._change("order", order); }

    /** A tab in use: what it shows put in its slot - the only thing there - or the slot empty. */
    _show(id) {
        var shown = this._shows.get(id) ?? null, slot = this._slot;
        while (slot.firstChild) slot.removeChild(slot.firstChild);
        if (shown !== null) slot.appendChild(shown);
        css.toggleClass(slot, mtp_empty, shown === null);
    }
}
