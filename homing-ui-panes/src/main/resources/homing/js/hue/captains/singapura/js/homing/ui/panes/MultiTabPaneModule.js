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
//                  new FixedMultiTabPane(new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), null), me.place(FixedMultiTabPane));
//   pane.add({ id: "alpha", title: "Alpha", closable?, detachable? })   a tab put in, its pane empty: the one in use
//   pane.mount("alpha", root, party?)   what a tab shows: a widget's root in its pane, its branch of the page grafted
//   pane.remove("alpha")   a tab taken out: its widget's root and branch let go - the widget's still, its caller's
//   pane.attach("alpha")   a tab afloat docked again, at its bar's end: the one in use
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

/** A tab torn off its bar, the hand carrying it - or none again: the pane takes it on. */
class MultiTabPaneCarry {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateTabCarry; }
    handle(change) { if (change.after !== "") this._pane._tear(change.after); }
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
    handle(change) { this._pane._floatDragged(this._id, change.after === true); }
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
        detaches: Object.freeze({ rest: StateTabDetachControl.rest, state: StateTabDetachControl })   // its own: each tab's detaches, told: no class
    });
    static BEHAVIOR = MultiTabPaneBehavior;

    /** How near the bar's middle a carried tab's chip comes to be taken back onto it, in pixels. */
    static CAPTURE = 8;
    /** Where the hand holds a floater it carries: on its head's chip, from its corner, in pixels. */
    static HOLD = Object.freeze({ x: 96, y: 19 });

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[MultiTabPane] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_multiTabPaneOwner);
        super(branch.elementMaker("pane"), fixed, FixedMultiTabPane, place ?? manageMultiTabPane);
        this.branch = branch;
        css.addClass(this.el, mtp_pane);
        this._tabs = new Map();   // its tabs' ids to each one's own: its branch, its pane, the root mounted in it - none until mounted - its title, and its floater while afloat
        this._detaches = new Map();   // its tabs' ids to how many times each was detached - none for one that cannot be: its own, a view of it told
        this._carrying = null;   // the tab the hand carries off its bar, in a floater under it
        this._offset = 0;   // how far below its chip's middle the hand took the tab carried
        this._dragged = null;   // the tab whose floater is in the hand, moved by its head
        this._floated = 0;   // the floaters made, each named afresh
        this._top = 0;   // the top of its floaters' stack
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
        this._tabs.set(id, { branch: branch, pane: pane, root: null, grafted: false, title: tab.title, floater: null });
        try { this._strip.add(tab); }
        catch (e) { this._tabs.delete(id); branch.dissolve(); throw e; }
        this._detaches.set(id, tab.detachable !== false ? { times: 0 } : null);
        this._change("detaches", this._detachesNow());
    }

    /** A tab afloat docked again by its caller, at its bar's end: its floater gone, what it shows back in its pane, the one in use. */
    attach(id) {
        var t = this._tabs.get(id);
        if (t === undefined || t.floater === null || this._carrying === id) throw new Error("[MultiTabPane] has no tab " + id + " afloat");
        this._land(t);
        this._strip.attach(id);
    }

    /** The ids of the tabs afloat, in its floaters - not the one the hand carries. */
    get afloat() {
        var out = [], carrying = this._carrying;
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
        if (this._carrying === id) this._carrying = null;
        if (this._dragged === id) this._dragged = null;
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
    dispose() {
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

    /**
     * A tab torn off its bar, as its listener hears it: carried in a floater of its own, made under the hand - its head
     * the tab's chip, its measure its slot's, on top - what it shows moved into it, never rebuilt.
     */
    _tear(id) {
        var t = this._tabs.get(id), slot = this._slot.getBoundingClientRect(), bar = this._strip.el.getBoundingClientRect(), press = this._behavior.press;
        var f = new FloatingPane(this.branch.createBranch("float-" + (++this._floated)),
            new FixedFloatingPane(new FixedTitleBar(null, new FixedTab(null), new FixedCloseButton())), this._place(FixedFloatingPane));
        f.title = t.title;
        f.onChange(StateFloatCloseControl, new MultiTabPaneFloatClose(this, id));
        f.onChange(StateDragging, new MultiTabPaneFloatDragged(this, id));
        this.el.appendChild(f.root);
        f.size(slot.width, slot.height);
        f.z = ++this._top;
        if (t.root !== null) { t.pane.removeChild(t.root); f.mount(t.root); }
        t.floater = f;
        this._carrying = id;
        this._offset = press !== null ? press.y - (bar.top + bar.height / 2) : 0;
        if (this._behavior.at !== null) this._follow(f, this._behavior.at.x, this._behavior.at.y);
    }

    /** A floater put under the hand: the hand on its head's chip. */
    _follow(f, x, y) {
        var r = this.el.getBoundingClientRect();
        f.at(x - r.left - MultiTabPane.HOLD.x, y - r.top - MultiTabPane.HOLD.y);
    }

    /** Whether the hand - its chip's middle - is on the bar: within CAPTURE of its middle, along it. */
    _onBar(x, y, offset) {
        var b = this._strip.el.getBoundingClientRect(), mid = b.top + b.height / 2;
        return Math.abs(y - offset - mid) <= MultiTabPane.CAPTURE && x >= b.left && x <= b.right;
    }

    /** The hand moved, down on it, as its behaviour hears it - before its parts: the tab carried follows it, and is taken back on the bar; a floater in the hand docks on it. */
    _handAt(x, y) {
        if (this._carrying !== null) {
            var t = this._tabs.get(this._carrying);
            this._follow(t.floater, x, y);
            if (this._onBar(x, y, this._offset)) this._back(this._carrying, x, y);
            return;
        }
        if (this._dragged !== null && this._onBar(x, y, 0)) this._back(this._dragged, x, y);
    }

    /** The hand let go, as its behaviour hears it - before its parts: the tab carried stays afloat - detached. */
    _handUp() {
        var id = this._carrying;
        if (id === null) return;
        this._carrying = null;
        this._strip.release(id);
        this._detaches.get(id).times++;
        this._change("detaches", this._detachesNow());
    }

    /** A tab carried, or a floater in the hand, back on the bar under the hand: its floater gone, its chip there, sliding on. */
    _back(id, x, y) {
        var t = this._tabs.get(id);
        if (this._carrying === id) this._carrying = null;
        if (this._dragged === id) this._dragged = null;
        this._land(t);
        this._strip.capture(id, x, y);
    }

    /** A tab's floater dropped: what it shows back in the tab's pane. */
    _land(t) {
        var f = t.floater;
        t.floater = null;
        if (t.root !== null) { f.unmount(); t.pane.appendChild(t.root); }
        f.dispose();
    }

    /** One of its floaters in the hand, or let go: on top while it is moved, the one in the hand kept. */
    _floatDragged(id, on) {
        var t = this._tabs.get(id);
        if (on) { this._dragged = id; if (t && t.floater !== null) t.floater.z = ++this._top; }
        else if (this._dragged === id) this._dragged = null;
    }

    /** One of its floaters' close pressed: that tab's close asked, as its chip's cross would. */
    _floatClose(id) { this._strip.closeAsked(id); }

    /** A tab in use: its pane shown, every other hidden - none hidden, never detached; the slot empty while it holds none. */
    _show(id) {
        this._tabs.forEach(function (t, tid) { css.toggleClass(t.pane, mtp_tab_content_hidden, tid !== id); });
        css.toggleClass(this._slot, mtp_empty, this._tabs.size === 0);
    }
}
