// =============================================================================
// TabStrip — the bar over a multi-tab pane: a chip for each tab it holds, then
// its one plus. Ported (RFC 0071) a step at a time. Which tabs it holds is its
// owner's to say, one at a time - add(tab), remove(id) (owner, 22 Oct): a tab
// put in is the one in use; a tab in use taken out hands over to the one now in
// its place, else the one before; the last may go. The bar owns its chips
// (owner, 22 Oct): it makes them, and every move of theirs is its own - a press
// on a chip makes it the one in use; held and moved along the bar, the chip
// slides as a browser's tab does: it goes where the hand goes, the chips it
// passes step aside, eased by their look, and let go it settles onto the place
// it is nearest. Its
// chips' states are its business alone: what its owner hears are the bar's own
// changes - which tab is in use (ActiveTab), the order of its tabs (TabOrder),
// told once a drag ends, if it changed anything, the closes asked of each tab -
// its chips' crosses (TabCloseControl): a count for each tab, none for one that
// cannot be closed, whose chip has no cross - and the adds asked of it, its plus
// pressed (TabAddControl). It handles none of them: what is closed or added is
// its owner's caller's to decide. A chip held and moved across the bar past 24px
// - where it can be detached - is torn off (owner, 23 Oct): out of the row, the
// others closing the gap, eased; and the bar says which tab the hand carries
// (TabCarry) for its owner to take on - it takes it back under the hand
// (capture), lets it go afloat (release), or docks it again (attach). Its count
// and its landing come as they are ported.
//
//   var strip = new TabStrip(branch.createBranch("bar"), new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), me.place(FixedTabStrip));
//   strip.add({ id: "alpha", title: "Alpha", closable?, detachable? })   a tab put in, at its end: the one in use
//   strip.remove("alpha")   a tab taken out
//   strip.onChange(StateTabCarry, listener)    a tab torn off, the hand carrying it - or none again
//   strip.capture("alpha", x, y)   a tab carried or afloat taken back onto the bar under the hand, the hand still down
//   strip.release("alpha")         the tab carried let go afloat: out of the order, still held
//   strip.attach("alpha")          a tab afloat docked again, at the end: the one in use
//   strip.closeAsked("alpha")      a close asked of a tab from elsewhere - its owner's floater's
//   strip.onChange(StateActiveTab, listener)   another tab in use: its id before and after
//   strip.onChange(StateTabOrder, listener)    its tabs in another order: before and after
//   strip.onChange(StateTabCloseControl, listener)   a tab's close asked, or a tab put in: before and after
//   strip.onChange(StateTabAddControl, listener)   its plus pressed: the count up by one
//   strip.active  strip.order  strip.closes  strip.adds  strip.current
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

/** One of its chips' crosses pressed: told to the strip, by the chip's tab id. */
class TabStripClose {
    constructor(strip, id) { this._strip = strip; this._id = id; Object.freeze(this); }
    canHandle(type) { return type === StateChipCloseControl; }
    handle() { this._strip._closeAsked(this._id); }
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
        order: Object.freeze({ rest: StateTabOrder.rest, state: StateTabOrder }),   // its tabs' ids, in order: no class
        closes: Object.freeze({ rest: StateTabCloseControl.rest, state: StateTabCloseControl }),   // the closes asked of each tab, passed on: no class
        carried: Object.freeze({ rest: StateTabCarry.rest, state: StateTabCarry })   // the tab the hand carries off it, for its owner: no class
    });
    static BEHAVIOR = TabStripBehavior;

    /** How far the hand moves a held chip before it is in the hand, in pixels: a press that wanders less is a press. */
    static DRAG_FROM = 4;

    /** How far across the bar a held chip's middle goes before it is torn off, in pixels. */
    static ESCAPE = 24;

    /** A tab's id: lower case words joined by a dash - its chip's branch is named for it. */
    static ID = /^[a-z0-9]+(-[a-z0-9]+)*$/;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[TabStrip] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_tabStripOwner);
        super(branch.elementMaker("strip"), fixed, FixedTabStrip, place ?? manageTabStrip);
        this.branch = branch;
        css.addClass(this.el, mtp_strip);
        this.el.setAttribute("role", "tablist");
        this._chips = new Map();   // its tabs' ids to their chips: its own
        this._closes = new Map();   // its tabs' ids to how many times each one's close was asked - none for one that cannot be closed: its own, a view of it told
        this._row = [];   // its tabs' ids as its chips stand now, a drag moving them
        this._titles = new Map();   // its tabs' ids to their titles: a chip made again from them, taken back
        this._tearable = new Set();   // the tabs that can be detached: torn off by the hand
        this._grabbed = null;   // the id of the chip held, or none
        this._slide = null;   // its slide, once the chip held has moved past its start
        this._settling = null;   // the slide let go, its chip easing onto its place until it is at rest
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
     * A tab put in, at its end, by its owner: its id - lower case words joined by a dash, none it holds - its title, and
     * whether it can be closed, unless it cannot (closable: false). Its chip is made, before the plus, with its cross where
     * it can be closed; the tab is put in its order and among its closes, one change each; and it is the one in use. A
     * slide in progress is let go where it began, first.
     */
    add(tab) {
        var t = tab || {};
        if (typeof t.id !== "string" || !TabStrip.ID.test(t.id)) throw new TypeError("[TabStrip] a tab's id is lower case words joined by a dash, not " + t.id);
        if (this._closes.has(t.id)) throw new Error("[TabStrip] holds a tab " + t.id + " already");
        if (t.closable !== undefined && typeof t.closable !== "boolean") throw new TypeError("[TabStrip] whether a tab can be closed is true or false, or unsaid - it can");
        if (t.detachable !== undefined && typeof t.detachable !== "boolean") throw new TypeError("[TabStrip] whether a tab can be detached is true or false, or unsaid - it can");
        this._letGo();
        this._titles.set(t.id, t.title);
        if (t.detachable !== false) this._tearable.add(t.id);
        this._closes.set(t.id, t.closable !== false ? { times: 0 } : null);
        this.el.insertBefore(this._chip(t.id).el, this._plus.el);
        this._row = this._row.concat([t.id]);
        this._change("order", this._row);
        this._change("closes", this._closesNow());
        this._select(t.id);
    }

    /** A chip of a tab it holds, made - its cross where it can be closed - and heard: held, and its close asked. */
    _chip(id) {
        var closable = this._closes.get(id) !== null;
        var chip = new Tab(this.branch.createBranch("tab-" + id), new FixedTab(closable ? new FixedCloseButton() : null), this._place(FixedTab));
        chip.title = this._titles.get(id);
        chip.onChange(StateHeld, new TabStripHeld(this, id));
        if (closable) chip.onChange(StateChipCloseControl, new TabStripClose(this, id));
        this._chips.set(id, chip);
        return chip;
    }

    /**
     * A tab taken out, by its owner: its chip and everything on its branch gone; out of its order and its closes, one
     * change each; and, were it in use, the one now in its place in use, else the one before - none when it was the last.
     * A slide in progress is let go where it began, first.
     */
    remove(id) {
        if (!this._closes.has(id)) throw new Error("[TabStrip] holds no tab " + id);
        this._letGo();
        if (this.carried === id) this._change("carried", "");
        var at = this._row.indexOf(id), was = this.active === id;
        if (this._chips.has(id)) { this._chips.get(id).dispose(); this._chips.delete(id); }
        this._row = this._row.filter(function (o) { return o !== id; });
        this._change("order", this._row);
        this._closes.delete(id);
        this._titles.delete(id);
        this._tearable.delete(id);
        this._change("closes", this._closesNow());
        if (was) this._select(this._row[at] ?? this._row[at - 1] ?? null);
    }

    /**
     * A tab carried off it, or afloat, taken back by its owner onto the bar under the hand - at the place nearest it -
     * the hand still down: in use, held, and sliding under the hand from there, as if it had never left. Its order is told
     * when the hand lets go, if it changed.
     */
    capture(id, x, y) {
        if (!this._closes.has(id) || this._chips.has(id)) throw new Error("[TabStrip] carries no tab " + id + ", and has none afloat");
        this._letGo();
        var chips = this._chips, at = this._row.filter(function (o) { var r = chips.get(o).el.getBoundingClientRect(); return x > r.left + r.width / 2; }).length;
        var chip = this._chip(id), row = this._row.slice();
        this.el.insertBefore(chip.el, at < row.length ? chips.get(row[at]).el : this._plus.el);
        row.splice(at, 0, id);
        this._row = row;
        if (this.carried === id) this._change("carried", "");
        this._select(id);
        this._grabbed = id;
        this._behavior.from(x, y);
        this._behavior.grab();
        this._slide = new TabStripSlide(this, row.map(function (o) { return chips.get(o); }), at, x);
        this._slide.to(x);
    }

    /** The tab the hand carries let go afloat, by its owner: carried no more, out of its order - still held. */
    release(id) {
        if (this.carried !== id) throw new Error("[TabStrip] carries no tab " + id);
        this._change("carried", "");
        this._change("order", this._row);
    }

    /** A tab afloat docked again by its owner, at its end: its chip made again, in its order, the one in use. */
    attach(id) {
        if (!this._closes.has(id) || this._chips.has(id) || this.carried === id) throw new Error("[TabStrip] has no tab " + id + " afloat");
        this._letGo();
        this.el.insertBefore(this._chip(id).el, this._plus.el);
        this._row = this._row.concat([id]);
        this._change("order", this._row);
        this._select(id);
    }

    /** The tab whose chip on it holds a node, by its id - its owner's menu asks - or none. */
    tabAt(node) { for (const [id, chip] of this._chips) if (chip.el.contains(node)) return id; return null; }

    /** A close asked of one of its tabs from elsewhere - its owner's floater's: one more close asked, as its cross's would be. */
    closeAsked(id) {
        if (!this._closes.has(id) || this._closes.get(id) === null) throw new Error("[TabStrip] has no tab " + id + " that can be closed");
        this._closeAsked(id);
    }

    /** The tab the hand carries off it, by its id; none while it carries none. */
    get carried() { return this._get("carried"); }

    /** The tab in use, by its id; none while it holds none. */
    get active() { return this._get("active"); }

    /** Its tabs' ids, in the order they stand on it. */
    get order() { return this._get("order"); }

    /** How many times each tab's close was asked, by its id - null for a tab that cannot be closed. */
    get closes() { return this._get("closes"); }

    /** How many adds were asked of it, from none: its plus's presses. */
    get adds() { return this._get("adds"); }

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** It and everything on its branch go together. */
    dispose() { this._letGo(); this.branch.dissolve(); }

    /** Its plus pressed, as its listener hears it: one more add asked. */
    _asked() { this._change("adds", this.adds + 1); }

    /** A chip's cross pressed, as its listener hears it: one more close asked of that tab - and nothing else. */
    _closeAsked(id) {
        this._closes.get(id).times++;
        this._change("closes", this._closesNow());
    }

    /** The closes it holds as they are now: a frozen view, each tab's count or none, in the order its tabs were given. */
    _closesNow() {
        var view = {};
        this._closes.forEach(function (c, id) { view[id] = c === null ? null : c.times; });
        return Object.freeze(view);
    }

    /** A chip held: made the one in use, and grabbed - the hand's moves heard until it lets go. */
    _pressed(id) {
        this._rest();
        this._select(id);
        this._grabbed = id;
        this._slide = null;
        this._behavior.grab();
    }

    /** A chip let go: slid, it lands on the place it is nearest - the row arranged, the chip settling onto it, at rest once there - and its order is told, if it changed. */
    _released(id) {
        if (this._grabbed !== id) return;
        var slide = this._slide;
        this._grabbed = null;
        this._slide = null;
        this._behavior.drop();
        if (slide === null) return;
        var to = slide.end();
        if (to !== slide.from) {
            var row = this._row.filter(function (o) { return o !== id; });
            row.splice(to, 0, id);
            this._row = row;
            this.el.insertBefore(this._chips.get(id).el, to + 1 < row.length ? this._chips.get(row[to + 1]).el : this._plus.el);
        }
        this._settling = slide;
        slide.settle();
        this._change("order", this._row);
    }

    /** A chip settling put at rest at once: another pressed, or its tabs about to change. */
    _rest() {
        var settling = this._settling;
        this._settling = null;
        if (settling !== null) settling.rest();
    }

    /**
     * The hand moved, a chip held - by how much since it went down, along the bar and across it: past its start, the chip
     * slides - lifted where it lies, under the hand from then on; past ESCAPE across the bar, where its tab can be
     * detached, it is torn off.
     */
    _dragTo(x, moved, across) {
        var id = this._grabbed;
        if (id === null) return;
        var dy = Number(across) || 0;
        if (this._slide === null) {
            if (Math.abs(moved) < TabStrip.DRAG_FROM && Math.abs(dy) < TabStrip.DRAG_FROM) return;
            var chips = this._chips;
            this._slide = new TabStripSlide(this, this._row.map(function (o) { return chips.get(o); }), this._row.indexOf(id), x - moved);
        }
        if (this._tearable.has(id) && Math.abs(dy) > TabStrip.ESCAPE) { this._tear(id); return; }
        this._slide.to(x);
    }

    /** The hand let go, as its behaviour hears it: the chip held let go. */
    _letGoAt() { if (this._grabbed !== null) this._released(this._grabbed); }

    /**
     * A chip torn off: out of the hand and out of the row - dropped, the others closing the gap, eased from where they
     * stood - the one now in its place in use, were it in use; and the tab the hand carries, said: its owner's to take on.
     */
    _tear(id) {
        var slide = this._slide, chips = this._chips, others = this._row.filter(function (o) { return o !== id; }), before = new Map();
        this._grabbed = null;
        this._slide = null;
        this._behavior.drop();
        slide.end();
        slide.rest();
        others.forEach(function (o) { before.set(o, chips.get(o).el.getBoundingClientRect().left); });
        var at = this._row.indexOf(id), was = this.active === id;
        chips.get(id).dispose();
        chips.delete(id);
        this._row = others;
        others.forEach(function (o) {
            var el = chips.get(o).el, dx = before.get(o) - el.getBoundingClientRect().left, ms = TabStripSlide._ease(el);
            if (Math.abs(dx) > 0.5 && ms > 0 && typeof el.animate === "function") el.animate([{ translate: dx + "px 0" }, { translate: "0 0" }], { duration: ms, easing: "ease" });
        });
        if (was) this._select(this._row[at] ?? this._row[at - 1] ?? null);
        this._change("carried", id);
    }

    /** A tab made the one in use: its chip selected, every other not - or none, none selected. */
    _select(id) {
        this._chips.forEach(function (tab, t) { tab.selected = t === id; });
        this._change("active", id === null ? "" : id);
    }

    /** A slide in progress let go where it began, and a chip settling, at rest at once: the chips back, nothing arranged, no order told - its tabs are about to change. */
    _letGo() {
        var slide = this._slide;
        this._grabbed = null;
        this._slide = null;
        this._behavior.drop();
        this._rest();
        if (slide !== null) { slide.end(); slide.rest(); }
    }
}
