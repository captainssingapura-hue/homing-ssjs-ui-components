// =============================================================================
// SingleTabPane — a pane of ONE tab: the in-transit vehicle a desk floats a
// tab in (RFC 0066 E3, appendix "tab-panes"), where MultiTabPane is the dock.
// A float is temporary, so it carries one tab and never more: no rail, no
// plus, no count, no keys of its own. Its bar is the tab's chip on a strip of
// the dock's look, and the whole bar — the chip with it — is its window's
// handle. A branch component: the caller makes a sub-branch for it and hands
// it in; dispose() dissolves it.
//
//   new SingleTabPane(branch, { host, slotId?, onEvent?, menus?, focus?, focusName?, onEmpty? })
//     host, slotId, onEvent, menus, focus, focusName   as MultiTabPane takes them
//     onEmpty(pane): its tab has left it — closed or let go
//
//   THE ONE TAB. take refuses a second: admits and canTake answer false while
//   it holds one, so a desk never offers it a tab, and a float is never a
//   drop target. A tab-pane is held, never owned, exactly as the dock holds
//   it — PaneTabs places its chip and its pane, PaneMenus opens its menu,
//   PaneKeys keeps the law — so a tab looks and behaves the same in both.
//
//   THE KEYS go to the tab. The pane is a road, not a place: a press anywhere
//   in it — the bar, the chip — claims for the pane, and the pane lands in
//   the tab at once, its widget activated. There is no bar to come back to,
//   so a yield from the widget passes the pane by (wouldHold is false). The
//   chip says "lent", and the bar is lit, while the keys are inside.
//
//   pane.bar() .barGround(target)   the bar, and whether a press there moves the window it
//       is the bar of: anywhere on it, the chip included, but the chip's cross
//   pane.take(tp, index?, later?) .settle(id) .letGo(tp) .canTake(tp) .admits(tp)
//   pane.select(tp) .menu(tp, at, byKey) .renamed(tp)   the host's, as MultiTabPane's
//   pane.removeTab(id) .switchTab(id) .retitle(id, title) .reicon(id, icon?)
//   pane.tabs() .activeTab() .has(id) .tabIndexOf(id) .count() .chipOf(i)
//   pane.contentElOf(id) .widgetOf(id) .tabPaneOf(id) .getState() .el .slotId .focus
//   pane.wouldOffer(m) .granted(by) .taken() .within(on, at)   the member's, called by the steward
//   pane.dispose()   refused while it holds its tab, which is its desk's
//
// Its reports are MultiTabPane's, on one sink: TabActivated, TabRenamed, and
// TabRemoved when its tab closes. `css` is injected with the styles import.
// =============================================================================

const _singleOwner = Object.freeze({ toString: () => "singleTabPane" });

class SingleTabPane {
    /** The kind a chip's menu is asked for: the Java TabMenu's, the dock's own. */
    static MENU = "tab";

    constructor(branch, opts) {
        if (!branch) throw new Error("[SingleTabPane] a branch of its own is required");
        if (!opts || !opts.host) throw new Error("[SingleTabPane] opts.host is required");
        branch.activate(_singleOwner);
        this._branch = branch;
        this.slotId = opts.slotId == null ? "main" : String(opts.slotId);
        this._sink = typeof opts.onEvent === "function" ? opts.onEvent : null;
        this._onEmpty = typeof opts.onEmpty === "function" ? opts.onEmpty : null;
        this._menus = opts.menus && typeof opts.menus.open === "function" ? opts.menus : null;
        this._tabs = [];          // none or one: { id, tab, tabPane, pinned, widget, chip, panel, menu }
        this._activeId = null;
        this._inside = false;
        this._disposed = false;

        var root = branch.createElement("pane", "div");
        css.addClass(root, mtp_pane);
        this.el = root;

        // the bar: the dock's strip, holding the chip alone. A press on it moves no native focus by the browser's default
        var bar = branch.createElement("bar", "div");
        css.addClass(bar, mtp_strip);
        bar.setAttribute("role", "tablist");
        bar.style.setProperty("--chip-fit", "100%");   // the chip never wider than its window: the label ellipsises, the cross stays
        bar.addEventListener("mousedown", function (ev) { ev.preventDefault(); });
        root.appendChild(bar);
        this._bar = bar;
        // what PaneTabs asks of a strip: the chip in, at the design's size, and out again at rest
        this._strip = {
            adopt: function (c) { css.size(c, null); css.aspect(c, null); bar.appendChild(c); return c; },
            remove: function (c) {
                c.setAttribute("aria-selected", "false");
                if (c._mark) { css.toggleClass(c._mark, mtp_chip_mark_on, false); css.extent(c._mark, null); }
                if (c.parentNode === bar) bar.removeChild(c);
            }
        };

        var above = opts.focus || focusParty.root;
        this.focus = above.createBranch(opts.focusName != null ? String(opts.focusName) : branch.name, this);
        this._offKeys = Keys.claimOn(root, this.focus.owner);

        this._content = branch.createElement("content", "div");
        css.addClass(this._content, mtp_content);
        root.appendChild(this._content);

        opts.host.appendChild(root);
    }

    _fire(ev) {
        if (!this._sink) return;
        try { this._sink(ev); }
        catch (e) { console.error("[SingleTabPane] onEvent threw on " + ev.kind + ":", e); }
    }

    // ── What PaneTabs reads and calls ─────────────────────────────────────
    _find(id) { return this._tabs.length && this._tabs[0].id === id ? 0 : -1; }
    _require(id) {
        if (this._find(id) < 0) throw new Error("[SingleTabPane] no tab '" + id + "' in slot '" + this.slotId + "'");
        return 0;
    }
    _pinnedCount() { return this._tabs.length && this._tabs[0].pinned ? 1 : 0; }
    _validate(tp) {
        if (this._tabs.length) throw new Error("[SingleTabPane] slot '" + this.slotId + "' carries its one tab already");
        PaneKeys.admit(this, tp);
    }
    _refresh() {}
    _activateNeighbour() {}
    _left() { if (this._tabs.length === 0 && this._onEmpty) this._onEmpty(this); }
    _show(id) {
        this._activeId = id;
        var e = this._tabs[0];
        if (!e) return;
        e.chip.setAttribute("aria-selected", "true");
        css.toggleClass(e.panel, mtp_tab_content_hidden, false);
        this._marks();
    }

    // ── The host's ────────────────────────────────────────────────────────
    canTake(tp) { return PaneTabs.admits(this, tp) && !tp.host(); }
    admits(tp) { return PaneTabs.admits(this, tp); }
    take(tp, index, later) { var at = PaneTabs.take(this, tp, null, PaneMenus.forChip(this, tp.id, SingleTabPane.MENU)); if (!later) this.settle(tp.id); return at; }
    settle(id) { if (this._activeId === null && this.has(id)) this.switchTab(id); }
    letGo(tp) { return PaneTabs.letGo(this, tp) < 0 ? null : tp; }
    select(tp) { if (this.has(tp.id)) this.switchTab(tp.id); }
    menu(tp, at, byKey) { return PaneTabs.menu(this, tp, at, byKey); }
    renamed(tp) { if (this.has(tp.id)) this._fire(PaneEvents.TabRenamed(this.slotId, tp.id, tp.title())); }

    // ── The surface ───────────────────────────────────────────────────────
    /** The tab-pane's close: it disposes its widget, then lets go here, which reports it. */
    removeTab(id) { return this._tabs[this._require(id)].tabPane.close(); }
    switchTab(id) {
        this._require(id);
        if (this._activeId === id) return;
        this._show(id);
        this._fire(PaneEvents.TabActivated(this.slotId, id));
    }
    retitle(id, title) { PaneTabs.retitle(this, id, title); return this; }
    reicon(id, icon) { PaneTabs.reicon(this, id, icon); return this; }
    tabs() { return this._tabs.length ? [this._tabs[0].id] : []; }
    activeTab() { return this._activeId; }
    has(id) { return this._find(id) >= 0; }
    tabIndexOf(id) { return this._find(id); }
    count() { return this._tabs.length; }
    chipOf(i) { return i === 0 && this._tabs.length ? this._tabs[0].chip : null; }
    contentElOf(id) { return this.has(id) ? this._tabs[0].panel : null; }
    widgetOf(id) { return this.has(id) ? this._tabs[0].widget : null; }
    tabPaneOf(id) { return this.has(id) ? (this._tabs[0].tabPane || null) : null; }
    getState() { return PaneEvents.state(this); }
    /** The tab's menu at its chip, by call; true when the steward took it. */
    menuByKey() { return PaneMenus.byKey(this); }

    /** The bar, and whether a target on it moves its window: all of it, the chip too, but the chip's cross. */
    bar() { return this._bar; }
    barGround(target) {
        var e = this._tabs[0];
        if (e && e.chip._close && e.chip._close.contains(target)) return false;
        return this._bar.contains(target);
    }

    // ── The member: a road to the tab, never a place ──────────────────────
    wouldHold() { return false; }
    wouldOffer(m) { return PaneKeys.wouldOffer(this, m); }
    /** Granted — a press on the bar, a claim — and passed straight into the tab; the browser's own focus arriving is left where it is. */
    granted(by) { if (by !== "native" && this._activeId !== null) { var w = this.widgetOf(this._activeId); if (w && w.activate) w.activate(); } }
    taken() {}
    within(on) { this._inside = !!on; this._marks(); }
    /** The keys inside the tab, said on its chip, lent, and on the bar, lit. */
    _marks() {
        var e = this._tabs[0];
        if (e && e.chip._mark) {
            css.toggleClass(e.chip._mark, mtp_chip_mark_on, this._inside);
            css.extent(e.chip._mark, this._inside ? 1 : null);
        }
        css.toggleClass(this._bar, mtp_strip_current, this._inside);
    }

    dispose() {
        if (this._disposed) return;
        if (this._tabs.length) throw new Error("[SingleTabPane] slot '" + this.slotId + "' still holds its tab-pane, which is its desk's: let it go first");
        this._disposed = true;
        if (this._offKeys) { this._offKeys(); this._offKeys = null; }
        if (this.el.parentNode) this.el.parentNode.removeChild(this.el);
        if (this.focus.owner.in) this.focus.owner.leave();
        this._branch.dissolve();
    }
}
