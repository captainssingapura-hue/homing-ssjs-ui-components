// =============================================================================
// MultiTabPane — a pane of tabs: its bar over its slot. A tab is where a widget
// is shown (owner, 22 Oct): its caller opens and closes widgets, and puts their
// tabs in and takes them out - add(tab), remove(id) - one at a time; the pane
// manages no tab in or out of its own. Each tab has a pane of its own, empty
// but for what its caller mounts in it - a widget's root, which the widget alone
// manages, and the widget's own branch of the page, grafted - in the slot, and
// shown while the tab is in use: never detached, only hidden, so a widget is
// never rebuilt. A tab put in is the one in use; a tab in use taken out hands
// over to the one now in its place, else the one before. A tab torn off its bar
// by the hand (owner, 23 Oct) is carried in a floater of the pane's own - its
// part, its head the tab's chip, its body what the tab shows, moved there, never
// rebuilt - under the hand; brought back within 8px of the bar, it is taken back
// onto it; let go off the bar, it stays afloat, still the pane's: detached, its
// TabDetachControl up - the pane's virtual detach manager's - and out of its
// bar's order. A floater dragged by its head back onto the bar docks again; its
// caller may dock it again too (attach). Its floater's close is its tab's close.
// A floater is sized as its tab says when it is put in - its size, its size's
// bounds - else a share of the slot; it is drawn on the pane's desk, where its
// caller made it with one - an element holding it and its other panes - else
// within the pane. A tab in the hand in a floater of its is in transit
// (TabInTransit): its caller tells its other panes to expect it, and the one
// whose bar its chip comes onto catches it (TabCaughtControl), told - its caller
// then moves the tab over at once, the hand still on it (owner, 23 Oct).
// The physics and the floaters are the pane's own: its caller hears the order,
// the closes, the detaches. Ported (RFC 0071) a step at a time. The bar is its
// own, and so is all that goes on on it - which tab is in use, the chips held
// and moved: what its caller hears are the changes that matter to it - the
// order of its tabs (TabOrder), told once a drag ends, if it changed it, and
// the closes asked of each tab (TabCloseControl), its bar's crosses', passed on.
// A press on its bar's plus is passed on - its own TabAddControl going up. What
// is closed or added, if anything, is its caller's to decide: it never closes a
// tab itself. Its keys come as they are ported.
//
//   var pane = new MultiTabPane(branch.createBranch("pane"),
//                  new FixedMultiTabPane(new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), null, { desk? }), me.place(FixedMultiTabPane));
//   pane.add({ id: "alpha", title: "Alpha", closable?, detachable?, float? })   a tab put in, its pane empty: the one in use
//       float: { size?, least?, most? }, each { w, h } - its floater's size, else a share of the slot, and its size's bounds
//   pane.expect("beta")   another pane's tab in transit, as its caller hears it - or null: caught should it come onto the bar
//   pane.onChange(StateTabInTransit, listener)   a tab of its in the hand in a floater, or none: pane.transit
//   pane.onChange(StateTabCaughtControl, listener)   another's tab caught on its bar, by its id: pane.caught - moved over by its caller
//   pane.mount("alpha", root, party?)   what a tab shows: a widget's root in its pane, its branch of the page grafted
//   pane.remove("alpha")   a tab taken out: its widget's root and branch let go - the widget's still, its caller's
//   pane.attach("alpha")   a tab afloat docked again, at its bar's end: the one in use
//   pane.menus(steward)   the page's menus' steward, once: a right-press on a chip opens that tab's menu
//   pane.onChange(StateTabDetachControl, listener)   a tab detached, or put in or taken out: before and after
//   pane.afloat    the ids of the tabs afloat, in its floaters
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

/** A tab torn off its bar, the hand carrying it - or none again: the pane's floaters take it on. */
class MultiTabPaneCarry {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateTabCarry; }
    handle(change) { if (change.after !== "") this._pane._floats.tear(change.after); }
}

/** One of its floaters' close pressed: that tab's close asked. */
class MultiTabPaneFloatClose {
    constructor(pane, id) { this._pane = pane; this._id = id; Object.freeze(this); }
    canHandle(type) { return type === StateFloatCloseControl; }
    handle() { this._pane._floatClose(this._id); }
}

/** One of its floaters in the hand, or let go of. */
class MultiTabPaneFloatDragged {
    constructor(pane, id) { this._pane = pane; this._id = id; Object.freeze(this); }
    canHandle(type) { return type === StateDragging; }
    handle(change) { this._pane._floats.inHand(this._id, change.after === true); }
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
        closes: Object.freeze({ rest: StateTabCloseControl.rest, state: StateTabCloseControl }),   // its bar's closes asked, passed on: no class
        detaches: Object.freeze({ rest: StateTabDetachControl.rest, state: StateTabDetachControl }),   // its own: each tab's detaches, told: no class
        transit: Object.freeze({ rest: StateTabInTransit.rest, state: StateTabInTransit }),   // its own: the tab of its in the hand in a floater, told: no class
        caught: Object.freeze({ rest: StateTabCaughtControl.rest, state: StateTabCaughtControl })   // its own: the tabs of others' it caught, told: no class
    });
    static BEHAVIOR = MultiTabPaneBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[MultiTabPane] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_multiTabPaneOwner);
        super(branch.elementMaker("pane"), fixed, FixedMultiTabPane, place ?? manageMultiTabPane);
        this.branch = branch;
        this._menu = null;
        css.addClass(this.el, mtp_pane);
        this._tabs = new Map();   // its tabs' ids to each one's own: its branch, its pane, the root mounted in it - none until mounted - its title, its floater's fixed object and size, and its floater while afloat
        this._detaches = new Map();   // its tabs' ids to how many times each was detached - none for one that cannot be: its own, a view of it told
        this._floated = 0;   // the floaters made, each named afresh
        this._floats = new MultiTabPaneFloats(this);   // its floaters: torn, carried, docked, and others' caught
        // its bar, the tab strip its fixed object holds: which tab is in use heard, its order and what it asks for passed on, a level up
        this._strip = new TabStrip(branch.createBranch("strip"), this.fixed.tabs, this._place(this.fixed.tabs));
        this._strip.onChange(StateTabAddControl, new MultiTabPaneAdd(this));
        this._strip.onChange(StateActiveTab, new MultiTabPaneShow(this));
        this._strip.onChange(StateTabOrder, new MultiTabPaneOrder(this));
        this._strip.onChange(StateTabCloseControl, new MultiTabPaneClose(this));
        this._strip.onChange(StateTabCarry, new MultiTabPaneCarry(this));
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
     * A tab put in by its caller, at its bar's end - its id, its title, whether it can be closed, what its floater is made
     * with: its own pane made in the slot, empty until its caller mounts in it; the tab the one in use. Refused for an id
     * it holds.
     */
    add(tab) {
        var id = tab && tab.id;
        if (this._tabs.has(id)) throw new Error("[MultiTabPane] holds a tab " + id + " already");
        if (typeof id !== "string" || !TabStrip.ID.test(id)) throw new TypeError("[MultiTabPane] a tab's id is lower case words joined by a dash, not " + id);
        // its floater's, should it be torn off (owner, 23 Oct): its size's bounds, made now and kept - immutable; its size the tab's, or a share of the slot
        var float = tab.float ?? {};
        if (typeof float !== "object" || Object.keys(float).some(function (k) { return k !== "size" && k !== "least" && k !== "most"; }))
            throw new TypeError("[MultiTabPane] a tab's float is { size, least, most }, each { w, h }, each left out at will");
        var size = float.size ?? null;
        if (size !== null && !(Number.isInteger(size.w) && size.w > 0 && Number.isInteger(size.h) && size.h > 0)) throw new TypeError("[MultiTabPane] a tab's floater's size is { w, h }, whole numbers above 0");
        var made = new FixedFloatingPane(new FixedTitleBar(null, new FixedTab(null), new FixedCloseButton()), { size: { least: float.least, most: float.most } });
        FloatingPane._bounded(made.size);
        var branch = this.branch.createBranch("tab-" + id);
        branch.activate(_multiTabPaneOwner, "multi-tab-pane tab " + id);   // the tab's own: its pane, and the widget grafted on it
        var pane = branch.createElement("pane", "div");
        css.addClass(pane, mtp_tab_content);
        css.addClass(pane, mtp_tab_content_hidden);
        this._slot.appendChild(pane);
        this._tabs.set(id, { branch: branch, pane: pane, root: null, grafted: false, title: tab.title, made: made, size: size && Object.freeze({ w: size.w, h: size.h }), floater: null });
        try { this._strip.add(tab); }
        catch (e) { this._tabs.delete(id); branch.dissolve(); throw e; }
        this._detaches.set(id, tab.detachable !== false ? { times: 0 } : null);
        this._change("detaches", this._detachesNow());
    }

    /** A tab afloat docked again by its caller, at its bar's end: its floater gone, what it shows back in its pane, the one in use. */
    attach(id) {
        var t = this._tabs.get(id);
        if (t === undefined || t.floater === null || this._floats.carrying === id) throw new Error("[MultiTabPane] has no tab " + id + " afloat");
        this._floats.land(t);
        this._strip.attach(id);
    }

    /** The ids of the tabs afloat, in its floaters - not the one the hand carries. */
    get afloat() {
        var out = [], carrying = this._floats.carrying;
        this._tabs.forEach(function (t, id) { if (t.floater !== null && id !== carrying) out.push(id); });
        return Object.freeze(out);
    }

    /** How many times each tab was detached, by its id - null for a tab that cannot be. */
    get detaches() { return this._get("detaches"); }

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
        if (t.floater !== null) t.floater.mount(root); else t.pane.appendChild(root);
    }

    /**
     * A tab taken out by its caller - once its widget is closed, or as it moves elsewhere: the widget's root out of its
     * pane and its branch of the page let go, both the widget's still; the tab's pane gone; the tab out of its bar, which
     * hands over the tab in use. Refused for a tab it does not hold.
     */
    remove(id) {
        var t = this._tabs.get(id);
        if (t === undefined) throw new Error("[MultiTabPane] holds no tab " + id);
        this._floats.forget(id);
        if (t.floater !== null) { t.floater.unmount(); t.floater.dispose(); t.floater = null; }
        else if (t.root !== null) t.pane.removeChild(t.root);
        if (t.grafted) t.branch.detach("widget");
        this._tabs.delete(id);
        this._strip.remove(id);
        t.branch.dissolve();
        this._detaches.delete(id);
        this._change("detaches", this._detachesNow());
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
    /** The page's menus' steward, handed in once: its tab menu, for a right-press on a chip of its bar. */
    menus(steward) { if (this._menu !== null) throw new Error("[MultiTabPane] its menus' steward is handed in once"); this._menu = new MultiTabPaneMenu(this, this._strip, steward); }

    /**
     * A tab of another pane's in the hand, as its caller says, hearing that pane's TabInTransit - or none: caught should its
     * chip come onto its bar, TabCaughtControl told; its caller then moves the tab over, the hand still on it - removed
     * there, put in and mounted here - and its chip slides on under the hand. Refused for a tab it holds.
     */
    expect(id) {
        if (id != null && (typeof id !== "string" || this._tabs.has(id))) throw new Error("[MultiTabPane] expects a tab of another pane's, by its id - not one it holds: " + id);
        this._floats.expect(id);
    }

    /** The tab of its in the hand in a floater of its own - carried, or dragged by its head - by its id: "" for none. */
    get transit() { return this._get("transit"); }

    /** How many times it caught each tab of others', by its id. */
    get caught() { return this._get("caught"); }

    dispose() {
        this._floats.drop();
        if (this._menu !== null) this._menu.drop();
        this._behavior.drop();
        this._tabs.forEach(function (t) {
            if (t.floater !== null) { t.floater.unmount(); t.floater.dispose(); }
            else if (t.root !== null) t.pane.removeChild(t.root);
            if (t.grafted) t.branch.detach("widget");
        });
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

    /** The detaches it holds as they are now: a frozen view, each tab's count or none, in the order its tabs were put in. */
    _detachesNow() {
        var view = {};
        this._detaches.forEach(function (d, id) { view[id] = d === null ? null : d.times; });
        return Object.freeze(view);
    }

    /** A floater for one of its tabs, made as the tab is torn off: its head the tab's chip, made as its tab said; heard. */
    _floater(id) {
        var t = this._tabs.get(id), f = new FloatingPane(this.branch.createBranch("float-" + (++this._floated)), t.made, this._place(FixedFloatingPane));
        f.title = t.title;
        f.onChange(StateFloatCloseControl, new MultiTabPaneFloatClose(this, id));
        f.onChange(StateDragging, new MultiTabPaneFloatDragged(this, id));
        return f;
    }

    /** The tab carried let go afloat: out of its bar's order, detached once more - told. */
    _afloat(id) {
        this._strip.release(id);
        this._detaches.get(id).times++;
        this._change("detaches", this._detachesNow());
    }

    /** A tab of its in the hand in a floater, or none: told. */
    _transit(id) { this._change("transit", id ?? ""); }

    /** A tab of another's caught on its bar: once more, told - its caller moving it over. */
    _caught(id) {
        var counts = Object.assign({}, this.caught);
        counts[id] = (counts[id] ?? 0) + 1;
        this._change("caught", Object.freeze(counts));
    }

    /** The hand moved, down on it or on one of its floaters, as its behaviour hears it - before its parts: its floaters' to take on. */
    _handAt(x, y) { this._floats.handAt(x, y); }

    /** The hand let go, as its behaviour hears it - before its parts. */
    _handUp() { this._floats.handUp(); }

    /** One of its floaters' close pressed: that tab's close asked, as its chip's cross would. */
    _floatClose(id) { this._strip.closeAsked(id); }

    /** A tab in use: its pane shown, every other hidden - none hidden, never detached; the slot empty while it holds none. */
    _show(id) {
        this._tabs.forEach(function (t, tid) { css.toggleClass(t.pane, mtp_tab_content_hidden, tid !== id); });
        css.toggleClass(this._slot, mtp_empty, this._tabs.size === 0);
    }
}
