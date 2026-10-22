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
// its owner's caller's to decide. Its count and its landing come as they are ported.
//
//   var strip = new TabStrip(branch.createBranch("bar"), new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), me.place(FixedTabStrip));
//   strip.add({ id: "alpha", title: "Alpha", closable? })   a tab put in, at its end: the one in use
//   strip.remove("alpha")   a tab taken out
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

/**
 * A chip slid along its bar, as a browser's tab bar slides one: lifted where it lies, it goes where the hand goes along the
 * row - the press kept as an offset within it - never before the first chip's place nor past the last's; the place it is
 * nearest is where it will land, and the chips between its own place and that one step aside, one place over, eased by
 * their look. Let go, they are back at once - the row is about to be arranged, and none must be seen sliding to where it
 * already is - and the chip settles onto its place from where the hand left it, eased as its look eases it. Nothing is
 * placed by hand: the chip in the hand carries --mtp-drag-x, a chip stepping aside --mtp-shift-x, each read by the class
 * that places it. The bar's alone.
 */
class TabStripSlide {
    /**
     * @param strip the bar
     * @param row   its chips, as they stand
     * @param from  the place of the chip slid
     * @param x     where the hand went down on it
     */
    constructor(strip, row, from, x) {
        this._strip = strip;
        this._row = row;
        this._slots = row.map(function (c) { var r = c.el.getBoundingClientRect(); return { left: r.left, top: r.top, width: r.width, height: r.height }; });
        this.from = from;
        this.dest = from;
        this._grab = x - this._slots[from].left;
        this._left = this._slots[from].left;
        this._chip = row[from];
        this._chip.dragging = true;
        css.addClass(this._chip.el, mtp_chip_dragging);   // lifted over the row along it: the bar's placing
        css.addClass(strip.el, mtp_strip_loose);   // nothing clipped meanwhile
    }

    /** The hand at x: the chip under it, within the row; the chips between its place and the one it is nearest, aside. */
    to(x) {
        var slots = this._slots;
        this._left = TabDrag.clamp(x - this._grab, slots, 0);
        this._chip.el.style.setProperty("--mtp-drag-x", (this._left - slots[this.from].left) + "px");
        var d = TabDrag.dest(this._left, slots, 0);
        if (d !== this.dest) { this.dest = d; this._aside(d, false); }
    }

    /** Let go: out of the hand, the others back at once; the place it lands at. */
    end() {
        this._chip.el.style.removeProperty("--mtp-drag-x");
        this._aside(this.from, true);
        css.removeClass(this._chip.el, mtp_chip_dragging);
        css.removeClass(this._strip.el, mtp_strip_loose);
        this._chip.dragging = false;
        return this.dest;
    }

    /** Once the row is arranged: from where the hand left it onto its place, eased as its look eases it. */
    settle() {
        var el = this._chip.el, dx = this._left - this._slots[this.dest].left;
        if (!(Math.abs(dx) > 0.5) || typeof el.animate !== "function") return;
        var d = String(getComputedStyle(el).transitionDuration || "").split(",")[0].trim();
        var ms = d.endsWith("ms") ? parseFloat(d) : parseFloat(d) * 1000;
        if (ms > 0) el.animate([{ translate: dx + "px 0" }, { translate: "0 0" }], { duration: ms, easing: "ease" });
    }

    /** The chips between its place and `to`, one place over; the rest where they stand - at once, the drag over. */
    _aside(to, over) {
        var pitch = TabDrag.pitch(this._slots);
        for (var j = 0; j < this._row.length; j++) {
            if (j === this.from) continue;
            var s = TabDrag.shift(j, this.from, to), el = this._row[j].el;
            if (s !== 0) { css.addClass(el, mtp_chip_shifted); el.style.setProperty("--mtp-shift-x", (s * pitch) + "px"); continue; }
            css.removeClass(el, mtp_chip_shifted);
            el.style.removeProperty("--mtp-shift-x");
            if (over && typeof el.getAnimations === "function") el.getAnimations().forEach(function (a) { a.cancel(); });   // whatever it eased towards, it is there now
        }
    }
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
        closes: Object.freeze({ rest: StateTabCloseControl.rest, state: StateTabCloseControl })   // the closes asked of each tab, passed on: no class
    });
    static BEHAVIOR = TabStripBehavior;

    /** How far the hand moves a held chip before it is in the hand, in pixels: a press that wanders less is a press. */
    static DRAG_FROM = 4;

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
        this._grabbed = null;   // the id of the chip held, or none
        this._slide = null;   // its slide, once the chip held has moved past its start
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
        if (this._chips.has(t.id)) throw new Error("[TabStrip] holds a tab " + t.id + " already");
        if (t.closable !== undefined && typeof t.closable !== "boolean") throw new TypeError("[TabStrip] whether a tab can be closed is true or false, or unsaid - it can");
        this._letGo();
        var closable = t.closable !== false;
        var chip = new Tab(this.branch.createBranch("tab-" + t.id), new FixedTab(closable ? new FixedCloseButton() : null), this._place(FixedTab));
        chip.title = t.title;
        chip.onChange(StateHeld, new TabStripHeld(this, t.id));
        if (closable) chip.onChange(StateChipCloseControl, new TabStripClose(this, t.id));
        this.el.insertBefore(chip.el, this._plus.el);
        this._chips.set(t.id, chip);
        this._row = this._row.concat([t.id]);
        this._change("order", this._row);
        this._closes.set(t.id, closable ? { times: 0 } : null);
        this._change("closes", this._closesNow());
        this._select(t.id);
    }

    /**
     * A tab taken out, by its owner: its chip and everything on its branch gone; out of its order and its closes, one
     * change each; and, were it in use, the one now in its place in use, else the one before - none when it was the last.
     * A slide in progress is let go where it began, first.
     */
    remove(id) {
        if (!this._chips.has(id)) throw new Error("[TabStrip] holds no tab " + id);
        this._letGo();
        var at = this._row.indexOf(id), was = this.active === id;
        this._chips.get(id).dispose();
        this._chips.delete(id);
        this._row = this._row.filter(function (o) { return o !== id; });
        this._change("order", this._row);
        this._closes.delete(id);
        this._change("closes", this._closesNow());
        if (was) this._select(this._row[at] ?? this._row[at - 1] ?? null);
    }

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
    dispose() { this._behavior.drop(); this.branch.dissolve(); }

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
        this._select(id);
        this._grabbed = id;
        this._slide = null;
        this._behavior.grab();
    }

    /** A chip let go: slid, it lands on the place it is nearest - the row arranged, the chip settling onto it - and its order is told, if it changed. */
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
        slide.settle();
        this._change("order", this._row);
    }

    /** The hand moved, a chip held: past its start, the chip slides - lifted where it lies, under the hand from then on. */
    _dragTo(x, moved) {
        if (this._grabbed === null) return;
        if (this._slide === null) {
            if (Math.abs(moved) < TabStrip.DRAG_FROM) return;
            var chips = this._chips;
            this._slide = new TabStripSlide(this, this._row.map(function (o) { return chips.get(o); }), this._row.indexOf(this._grabbed), x - moved);
        }
        this._slide.to(x);
    }

    /** A tab made the one in use: its chip selected, every other not - or none, none selected. */
    _select(id) {
        this._chips.forEach(function (tab, t) { tab.selected = t === id; });
        this._change("active", id === null ? "" : id);
    }

    /** A slide in progress let go where it began: the chips back, nothing arranged, no order told - its tabs are about to change. */
    _letGo() {
        var slide = this._slide;
        this._grabbed = null;
        this._slide = null;
        this._behavior.drop();
        if (slide !== null) slide.end();
    }
}
