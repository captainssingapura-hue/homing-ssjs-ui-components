// =============================================================================
// MultiTabPane — a pane of tabs: its bar over its slot. A tab is where a widget
// is shown (owner, 22 Oct): its caller opens and closes widgets, and puts their
// tabs in and takes them out - add(tab), remove(id) - one at a time; the pane
// manages no tab in or out of its own. Each tab has a pane of its own, empty
// but for what its caller mounts in it - a widget's root, which the widget alone
// manages, and the widget's own branch of the page, grafted - in the slot, and
// shown while the tab is in use: never detached, only hidden, so a widget is
// never rebuilt. A tab put in is the one in use; a tab in use taken out hands
// over to the one now in its place, else the one before. Ported (RFC 0071) a
// step at a time. The bar is its
// own, and so is all that goes on on it - which tab is in use, the chips held
// and moved: what its caller hears are the changes that matter to it - the
// order of its tabs (TabOrder), told once a drag ends, if it changed it, and
// the closes asked of each tab (TabCloseControl), its bar's crosses', passed on.
// A press on its bar's plus is passed on - its own TabAddControl going up. What
// is closed or added, if anything, is its caller's to decide: it never closes a
// tab itself. Its keys come as they are ported.
//
//   var pane = new MultiTabPane(branch.createBranch("pane"),
//                  new FixedMultiTabPane(new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), null), me.place(FixedMultiTabPane));
//   pane.add({ id: "alpha", title: "Alpha", closable? })   a tab put in, its pane empty: the one in use
//   pane.mount("alpha", root, party?)   what a tab shows: a widget's root in its pane, its branch of the page grafted
//   pane.remove("alpha")   a tab taken out: its widget's root and branch let go - the widget's still, its caller's
//   pane.onChange(StateTabOrder, listener)   its tabs in another order: before and after
//   pane.onChange(StateTabCloseControl, listener)   a tab's close asked, or a tab put in: before and after
//   pane.closes    how many times each tab's close was asked, by its id - null for one that cannot be closed
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

/** A close asked of one of its bar's tabs: its own, told to its caller. */
class MultiTabPaneClose {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateTabCloseControl; }
    handle(change) { this._pane._closesNow(change.after); }
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
        order: Object.freeze({ rest: StateTabOrder.rest, state: StateTabOrder }),   // its bar's order, passed on: no class
        closes: Object.freeze({ rest: StateTabCloseControl.rest, state: StateTabCloseControl })   // its bar's closes asked, passed on: no class
    });
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[MultiTabPane] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_multiTabPaneOwner);
        super(branch.elementMaker("pane"), fixed, FixedMultiTabPane, place ?? manageMultiTabPane);
        this.branch = branch;
        css.addClass(this.el, mtp_pane);
        this._tabs = new Map();   // its tabs' ids to each one's own: its branch, its pane, and the root mounted in it - none until mounted
        // its bar, the tab strip its fixed object holds: which tab is in use heard, its order and what it asks for passed on, a level up
        this._strip = new TabStrip(branch.createBranch("strip"), this.fixed.tabs, this._place(this.fixed.tabs));
        this._strip.onChange(StateTabAddControl, new MultiTabPaneAdd(this));
        this._strip.onChange(StateActiveTab, new MultiTabPaneShow(this));
        this._strip.onChange(StateTabOrder, new MultiTabPaneOrder(this));
        this._strip.onChange(StateTabCloseControl, new MultiTabPaneClose(this));
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

    /**
     * A tab put in by its caller, at its bar's end - its id, its title, whether it can be closed: its own pane made in the
     * slot, empty until its caller mounts in it; the tab the one in use. Refused for an id it holds.
     */
    add(tab) {
        var id = tab && tab.id;
        if (this._tabs.has(id)) throw new Error("[MultiTabPane] holds a tab " + id + " already");
        if (typeof id !== "string" || !TabStrip.ID.test(id)) throw new TypeError("[MultiTabPane] a tab's id is lower case words joined by a dash, not " + id);
        var branch = this.branch.createBranch("tab-" + id);
        branch.activate(_multiTabPaneOwner, "multi-tab-pane tab " + id);   // the tab's own: its pane, and the widget grafted on it
        var pane = branch.createElement("pane", "div");
        css.addClass(pane, mtp_tab_content);
        css.addClass(pane, mtp_tab_content_hidden);
        this._slot.appendChild(pane);
        this._tabs.set(id, { branch: branch, pane: pane, root: null, grafted: false });
        try { this._strip.add(tab); }
        catch (e) { this._tabs.delete(id); branch.dissolve(); throw e; }
    }

    /**
     * What one of its tabs shows, mounted by its caller: a widget's root - its own, which it alone manages - in the tab's
     * pane, and the widget's own branch of the page, where it has one, grafted on the tab's. Once a tab.
     */
    mount(id, root, party) {
        var t = this._tabs.get(id);
        if (t === undefined) throw new Error("[MultiTabPane] holds no tab " + id);
        if (t.root !== null) throw new Error("[MultiTabPane] shows something for " + id + " already");
        if (!root || root.nodeType !== 1) throw new TypeError("[MultiTabPane] what a tab shows is a widget's root, an element");
        if (party != null) { t.branch.graft("widget", party); t.grafted = true; }
        t.root = root;
        t.pane.appendChild(root);
    }

    /**
     * A tab taken out by its caller - once its widget is closed, or as it moves elsewhere: the widget's root out of its
     * pane and its branch of the page let go, both the widget's still; the tab's pane gone; the tab out of its bar, which
     * hands over the tab in use. Refused for a tab it does not hold.
     */
    remove(id) {
        var t = this._tabs.get(id);
        if (t === undefined) throw new Error("[MultiTabPane] holds no tab " + id);
        if (t.root !== null) t.pane.removeChild(t.root);
        if (t.grafted) t.branch.detach("widget");
        this._tabs.delete(id);
        this._strip.remove(id);
        t.branch.dissolve();
        this._show(this.active);
    }

    /** Its tabs' ids, in the order they stand on its bar. */
    get order() { return this._get("order"); }

    /** How many times each tab's close was asked, by its id - null for a tab that cannot be closed. */
    get closes() { return this._get("closes"); }

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

    /** It and everything on its branch go together; what its tabs show is its caller's: let go, first. */
    dispose() {
        this._tabs.forEach(function (t) { if (t.root !== null) t.pane.removeChild(t.root); if (t.grafted) t.branch.detach("widget"); });
        this._tabs.clear();
        this._strip.dispose();
        this.branch.dissolve();
    }

    /** Its bar's plus pressed, as its listener hears it: one more add asked. */
    _asked() { this._change("adds", this.adds + 1); }

    /** Its bar's tabs in another order, as its listener hears it. */
    _ordered(order) { this._change("order", order); }

    /** A close asked of one of its bar's tabs, or a tab put in, as its listener hears it. */
    _closesNow(closes) { this._change("closes", closes); }

    /** A tab in use: its pane shown, every other hidden - none hidden, never detached; the slot empty while it holds none. */
    _show(id) {
        this._tabs.forEach(function (t, tid) { css.toggleClass(t.pane, mtp_tab_content_hidden, tid !== id); });
        css.toggleClass(this._slot, mtp_empty, this._tabs.size === 0);
    }
}
