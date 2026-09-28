// =============================================================================
// MultiTabPane — one pane of tabs: a strip of chips over one content area,
// each tab a TAB-PANE of a desk's (RFC 0066 E3, appendix "tab-panes"), held
// here and never owned. A branch component: the caller makes a sub-branch for
// it and hands it in; dispose() dissolves it.
//
//   new MultiTabPane(branch, { host, slotId?, addable?, onEvent?, menus?, stripMenu?, focus?, focusName?, keys?, onEmpty?, onClose? })
//     onEmpty(pane): its last tab has left it — closed or let go — for a
//             holder whose pane lives only while it holds something: a float
//     onClose(): the bar's own cross at its end, for a pane that is a window's
//             content and its strip that window's one bar: a float's
//     branch: the pane's own, handed unactivated
//     host:   a flex column; the pane is its item and fills it.
//     focus:  the focus branch the pane joins — the page's root unless said,
//             the desk's for a floating dock. The pane holds a branch of its
//             own under it, pane.focus, named focusName (the branch's name
//             unless said): THE DOCK'S BRANCH, which every tab's widget joins.
//     stripMenu: the kind a right-click on the strip's own ground opens, with
//             { pane } bound - the page's own kind, since what it offers is
//             about where the pane sits, which the pane knows nothing of
//     menus:  the page's ContextMenuSteward, when the page offers menus: a
//             right-click on a chip, or the ContextMenu key / Shift+F10 on it,
//             asks the steward for MultiTabPane.MENU — the kind "tab", which
//             the pane's declaration names as its need and any site serving
//             the pane holds — bound to { pane, tab, anchor }: this pane, the
//             tab-pane, its chip. The steward says whether it took the
//             request; only then is the browser's menu suppressed. What a
//             pick does — detach, close — is the page's handler for "tab".
//
//   THE LAW: a tab's widget is logically focusable. It is a member of the
//   dock's branch — it joined pane.focus, or the branch of the dock it came
//   from, and exposes its membership as widget.focus — and it answers
//   activate(). What it contains natively is its own affair, encapsulated:
//   the pane never sees a native control. take refuses a tab-pane whose
//   widget is not, and adopts its membership from wherever it was.
//
//   THE KEYS ARE SWAPPABLE. `keys` is a scheme or a list of them, PaneKeys
//   unless said, and pane.keys(...) swaps them live. A scheme answers two
//   doors — keyDown(pane, ev) while the pane holds the keys, chord(pane, ev)
//   while something below it is natively focused and a chord got past — so a
//   scheme that means to work in both says so once. PaneKeys is below;
//   BrowserKeys, beside it, is Ctrl+Tab and wraps.
//
//   THE KEYS, while the pane holds them — nothing natively focused, the pane
//   the holder by a press on a chip or the frame, a widget's yield caught, or
//   a claim on open. Level 1, the container's own, and nothing else:
//     ← →              the active tab moves to the previous / next, at once
//     Home / End       the first / last tab
//     Shift+← / →      the active tab moves one slot along the rail, staying active: moveTab
//     Shift+↓          DetachRequested(slotId, tabId): the holder with a desk floats it
//     Enter            the active tab's widget activates itself — a claim; the pane's never for it
//     Escape           the pane yields, up the tree
//     Shift+F10, ContextMenu   the active tab's menu, when the page offers menus
//   Level 2 is the widget's: its own keys, its Escape yielding back — the pane
//   answers wouldHold yes. A chip takes NO native focus: a press on it selects
//   the tab, takes the native focus away from whatever had it, and claims the
//   pane, so ← → work after every mouse press. No keydown listener of its own.
//
//   pane.pick(kinds, onPick)     a PICKER, the transient pane the pane owns (TabPicker): shown
//       in place of the tab it shows, [{ id, label }] a button each and Cancel. A pick is
//       onPick(kind) - it asks, the holder opens - and the picker goes; so it goes on Cancel,
//       unpick(), and when any tab is shown. No tab, nothing reported. .picking()
//   pane.removeTab(id)           → the tab-pane, closed: its widget disposed, then
//       TabRemoved(slotId, tp, fromIndex) where it was; a neighbour is activated after.
//   pane.switchTab(id)           → TabActivated(slotId, id): one tab-pane's pane shown, the rest hidden
//   pane.retitle(id, title)      the tab's name, now: said to the tab-pane, whose chip shows it;
//                                any rename of a tab it holds → TabRenamed(slotId, tabId, title)
//   pane.reicon(id, icon?)       the tab's icon, now: an element of the holder's — a favicon,
//       whatever it is made of — shown before the label; none takes it away.
//       Neither is a mutation of the arrangement, so neither is reported.
//   pane.moveTab(id, destIndex)  → TabMoved(slotId, tab, srcIndex, slotId, destIndex),
//       destIndex being where the tab ends up. A drag on the strip is this.
//   pane.tabs() .activeTab() .has(id) .tabIndexOf(id) .count()
//   pane.canAdd() .setAddEnabled(b) .setRoom(b)   the plus is on while the holder has it on
//       and its desk has room: a pane has no limit of its own — its bar scrolls — and
//       the limit is the desk's, which says setRoom(false) while it is spent
//   pane.size(s?) .aspect(a?)    the chips' size and aspect, −1..1, null the design's
//   pane.contentElOf(id) .widgetOf(id) .getState() .el .slotId .focus
//   pane.keyDown(ev) .wouldHold() .wouldOffer(m) .granted(by) .taken(by) .within(on, at)
//       the member's, called by the steward. The frame's mark — held, candidate —
//       is the steward's to write, on the root it enrolled (RFC 0066 E3, keyboard
//       §17.5); the pane marks its active chip and lights its bar, and when told
//       the focus is inside it, shows the tab it is in
//   pane.menuByKey() .menuByGround(at) .requestDetach() .yieldKeys()   what the
//       keys and a right-click do, by call; the menus themselves are PaneMenus'
//   pane.bar() .barGround(target)   the strip, and whether a target is its own ground and
//       not a chip or a control: for a float, whose frame that ground moves
//   pane.dispose()               → the pane out of its host and the tree, its branch dissolved;
//       refused while it holds a tab-pane, which is its desk's: let those go first
//
//   THE HOST (RFC 0066 E3, appendix "tab-panes"). A tab-pane is held, never
//   owned: its chip and its pane are its own, minted once, and they are placed
//   here as they are, never minted or dissolved here.
//   pane.canTake(tp)             → whether take would: by the law, the id not here,
//       and the tab-pane in no other host
//   pane.admits(tp)              → whether it would, were the tab-pane let go where it
//       is: what a mover asks before anything leaves
//   pane.tabPaneOf(id)           → the tab-pane held under that id, or null
//   pane.take(tp, index?, later?) → the index it took: its chip in the strip at this
//       strip's size, armed for the rail, its pane hidden in the content, its
//       widget's membership adopted under this pane's branch; shown if nothing was —
//       or, later, when settle(id) is called: a desk says the arrival first, then
//       the pane shows it. Arrivals are the desk's to report; the pane says TabActivated
//   pane.settle(id)              shown, if nothing is
//   pane.letGo(tp)               → the tab-pane, or null when it is not here: its
//       chip and pane out, nothing dissolved, a neighbour activated. When the
//       tab-pane is closing, TabRemoved(slotId, tp, fromIndex); a move is the
//       desk's to report
//   pane.select(tp) .menu(tp, at, byKey)   what its chip asks, while it is here
//
// The pane is a dock. A tab-pane leaves it by letGo, which its desk calls,
// the strip's own drag staying on its rail for now. A tab-pane from outside
// is offered by dropAt(clientX, clientY): over the strip — the dock's
// landing, not its content, since docks may tile a box and a float let go
// over content stays afloat — it marks where the tab would land and answers
// the index, elsewhere −1; the pane wears the drop-target word while an offer
// stands, and dropClear() ends it. The desk's move is the drop.
//
// Every mutation the pane makes is one event on one sink, onEvent(ev): a
// frozen object from PaneEvents tagged by kind — TabRemoved, TabMoved on its
// rail, TabActivated, AddRequested, DetachRequested — whose fields are the
// Java PaneEvent records' components. An arrival, and a move from host to
// host, is the desk's to report (TabAdded, TabMoved). The shape is data, so
// an event goes into a log or a checkpoint as it is. slotId is the pane's
// own, given or "main"; a move within one pane names it as both ends.
//
// The pane never calls setActive: which tab's widget is active is the
// holder's to decide, from TabActivated, as the studio's focus machinery
// decides it there. A chip is activated when it is pressed — before any
// release — so the tab in the hand is the active one through a reorder or
// a pull off the strip. Pinned tabs sit first, cannot be closed and are not
// dragged; a drop never lands before them.
//
// A tab-pane's chip and pane are its own, minted once on its own branch
// under its desk: the pane places them and takes them out, and never mints
// or dissolves one.
//
// The strip is TabStrip's, the keys PaneKeys'; `css` is injected with the
// styles import; the focus party's root, `focusParty`, is the branch a pane
// joins unless told.
// =============================================================================

const _paneOwner = Object.freeze({ toString: () => "multiTabPane" });

class MultiTabPane {
    /**
     * The kind a right-click on the strip's own ground asks for, when the
     * page named one: opened with { pane } bound, and the pane knows nothing
     * of what it says. Splitting the room a dock sits in, closing the region:
     * those belong to whoever placed the pane, so the kind is theirs too.
     */
    /** The kind a chip's menu is asked for: the Java TabMenu's. */
    static MENU = "tab";

    constructor(branch, opts) {
        if (!branch) throw new Error("[MultiTabPane] a branch of its own is required");
        if (!opts || !opts.host) throw new Error("[MultiTabPane] opts.host is required");
        var self = this;
        branch.activate(_paneOwner);
        this._branch = branch;
        this.slotId = opts.slotId == null ? "main" : String(opts.slotId);
        this._addEnabled = opts.addable !== false;
        this._room = true;   // its desk's word: whether a new tab would fit anywhere
        this._schemes = PaneSchemes.of(opts.keys);   // the list of schemes; _keys() is the method that marks the chip and the bar
        this._sink = typeof opts.onEvent === "function" ? opts.onEvent : null;
        this._onEmpty = typeof opts.onEmpty === "function" ? opts.onEmpty : null;
        this._menus = opts.menus && typeof opts.menus.open === "function" ? opts.menus : null;
        this._stripMenu = this._menus && typeof opts.stripMenu === "string" && opts.stripMenu ? opts.stripMenu : null;
        this._tabs = [];          // entries in strip order: { id, tab, tabPane, pinned, widget, chip, panel, menu }
        this._activeId = null;
        this._disposed = false;
        this._picker = null;      // { branch, root } while a picker is shown
        this._picks = 0;

        // ── The frame ─────────────────────────────────────────────────────
        var root = branch.createElement("pane", "div");
        css.addClass(root, mtp_pane);
        this.el = root;

        this._strip = new TabStrip(branch.createBranch("strip"), {
            onAdd: opts.addable === false ? null : function () { if (self.canAdd()) self._fire(PaneEvents.AddRequested(self.slotId)); },
            onDrop: function (chip, dest) { var i = self._findChip(chip); if (i >= 0) self.moveTab(self._tabs[i].id, dest); },
            onGroundMenu: this._stripMenu ? function (at) { return self.menuByGround(at); } : null,
            onClose: typeof opts.onClose === "function" ? opts.onClose : null
        });
        root.appendChild(this._strip.el);
        // the dock's branch of the focus party, held by the pane; a press anywhere in the frame claims for the pane
        // unless a member inside is nearer
        var above = opts.focus || focusParty.root;
        this.focus = above.createBranch(opts.focusName != null ? String(opts.focusName) : branch.name, this);
        this._offKeys = Keys.claimOn(root, this.focus.owner);

        this._content = branch.createElement("content", "div");
        css.addClass(this._content, mtp_content);
        root.appendChild(this._content);

        this._empty = branch.createElement("empty", "div");
        css.addClass(this._empty, mtp_empty);
        this._empty.textContent = "No tabs";
        this._content.appendChild(this._empty);

        opts.host.appendChild(root);
        this._refresh();
    }

    // ── Reporting ─────────────────────────────────────────────────────────
    _fire(ev) {
        if (!this._sink) return;
        try { this._sink(ev); }
        catch (e) { console.error("[MultiTabPane] onEvent threw on " + ev.kind + ":", e); }
    }

    // ── State ─────────────────────────────────────────────────────────────
    _find(id) {
        for (var i = 0; i < this._tabs.length; i++) if (this._tabs[i].id === id) return i;
        return -1;
    }
    _findChip(chip) {
        for (var i = 0; i < this._tabs.length; i++) if (this._tabs[i].chip === chip) return i;
        return -1;
    }
    _require(id) {
        var i = this._find(id);
        if (i < 0) throw new Error("[MultiTabPane] no tab '" + id + "' in slot '" + this.slotId + "'");
        return i;
    }
    _pinnedCount() {
        var n = 0;
        while (n < this._tabs.length && this._tabs[n].pinned) n++;
        return n;
    }
    _validate(tab) { PaneKeys.admit(this, tab); }

    // ── The chips and the panels: a tab-pane's own, PaneTabs' to place and take out ──
    _refresh() {
        this._strip.arrange(PaneTabs.chips(this));
        this._strip.count(this._tabs.length, this.canAdd());
        css.toggleClass(this._empty, mtp_tab_content_hidden, this._tabs.length > 0 || !!this._picker);
    }
    _show(id) {
        this._dropPicker();
        this._activeId = id;
        var active = null;
        for (var i = 0; i < this._tabs.length; i++) {
            var on = this._tabs[i].id === id;
            if (on) active = this._tabs[i].chip;
            css.toggleClass(this._tabs[i].panel, mtp_tab_content_hidden, !on);
        }
        this._strip.select(PaneTabs.chips(this), active);
        if (this._holds || this._inside) this._keys();   // where the keys are goes with the tab that is shown
    }
    /** After a tab left index i: the one now there, else the one before, becomes active. */
    _activateNeighbour(i) {
        if (this._activeId !== null) return;
        var next = this._tabs[i] || this._tabs[i - 1];
        if (next) this.switchTab(next.id);
    }

    // ── The picker: a transient pane the pane owns, shown in place of the tab it shows ──
    pick(kinds, onPick) {
        if (typeof onPick !== "function") throw new Error("[MultiTabPane] pick wants onPick(kind): the picker asks, and opens nothing");
        this._dropPicker();
        var self = this, b = this._branch.createBranch("picker-" + (++this._picks));
        var view = new TabPicker(b, { kinds: kinds, onPick: function (k) { self.unpick(); onPick(k); }, onCancel: function () { self.unpick(); } });
        this._picker = { branch: b, root: view.root };
        for (var i = 0; i < this._tabs.length; i++) css.addClass(this._tabs[i].panel, mtp_tab_content_hidden);
        css.addClass(this._empty, mtp_tab_content_hidden);
        this._content.appendChild(view.root);
        view.focus();
        return this;
    }
    picking() { return !!this._picker; }
    /** The picker called off: the tab it stood in for shown again. Nothing reported: the pane showed nothing new. */
    unpick() { if (this._dropPicker()) { this._show(this._activeId); this._refresh(); } }
    _dropPicker() { var p = this._picker; if (!p) return false; this._picker = null; if (p.root.parentNode) p.root.parentNode.removeChild(p.root); p.branch.dissolve(); return true; }

    // ── The surface ───────────────────────────────────────────────────────
    /** The tab-pane's close: it disposes its widget, then lets go here, which reports it. */
    removeTab(id) { return this._tabs[this._require(id)].tabPane.close(); }
    /** A tab has left: the holder told when it was the last. */
    _left() { if (this._tabs.length === 0 && this._onEmpty) this._onEmpty(this); }
    // ── The host's: tab-panes, held and never owned — PaneTabs does the work ──
    canTake(tp) { return PaneTabs.admits(this, tp) && !tp.host(); }
    admits(tp) { return PaneTabs.admits(this, tp); }
    take(tp, index, later) { var at = PaneTabs.take(this, tp, index == null ? null : index | 0, PaneMenus.forChip(this, tp.id, MultiTabPane.MENU)); if (!later) this.settle(tp.id); return at; }
    settle(id) { if (this._activeId === null && this.has(id)) this.switchTab(id); }
    letGo(tp) { return PaneTabs.letGo(this, tp) < 0 ? null : tp; }
    select(tp) { if (this.has(tp.id)) this.switchTab(tp.id); }
    menu(tp, at, byKey) { return PaneTabs.menu(this, tp, at, byKey); }

    /** A tab from outside, offered at a point: the index it would take on the strip, or −1 when the point is not on the strip. */
    dropAt(x, y) {
        var index = this._strip.at(x, y);
        if (index < 0) { css.removeClass(this.el, mtp_dock_target); return -1; }
        css.addClass(this.el, mtp_dock_target);
        return index;
    }
    dropClear() { this._strip.unmark(); css.removeClass(this.el, mtp_dock_target); }
    /** The tab's name, now: the pane shows what the holder calls it. */
    retitle(id, title) { PaneTabs.retitle(this, id, title); return this; }
    /** A tab-pane it holds was renamed: said here, where it is. */
    renamed(tp) { if (this.has(tp.id)) this._fire(PaneEvents.TabRenamed(this.slotId, tp.id, tp.title())); }
    /** The tab's icon, now: the holder's element, or none. */
    reicon(id, icon) { PaneTabs.reicon(this, id, icon); return this; }

    switchTab(id) {
        this._require(id);
        if (this._activeId === id) return;
        this._show(id);
        this._fire(PaneEvents.TabActivated(this.slotId, id));
    }
    moveTab(id, destIndex) {
        var src = this._require(id);
        var entry = this._tabs[src];
        var lo = entry.pinned ? 0 : this._pinnedCount();
        var hi = entry.pinned ? this._pinnedCount() - 1 : this._tabs.length - 1;
        var dest = Math.min(Math.max(destIndex | 0, lo), hi);
        if (dest === src) return false;
        this._tabs.splice(src, 1);
        this._tabs.splice(dest, 0, entry);
        this._refresh();
        this._fire(PaneEvents.TabMoved(this.slotId, entry.tab, src, this.slotId, dest));
        return true;
    }
    tabs() { var ids = []; for (var i = 0; i < this._tabs.length; i++) ids.push(this._tabs[i].id); return ids; }
    activeTab() { return this._activeId; }
    has(id) { return this._find(id) >= 0; }
    tabIndexOf(id) { return this._find(id); }
    count() { return this._tabs.length; }
    canAdd() { return this._addEnabled && this._room; }
    setAddEnabled(on) { this._addEnabled = !!on; this._refresh(); }
    setRoom(on) { this._room = !!on; this._refresh(); }
    size(s) { this._strip.size(s); }
    aspect(a) { this._strip.aspect(a); }
    chipOf(i) { return i < 0 || i >= this._tabs.length ? null : this._tabs[i].chip; }
    contentElOf(id) { var i = this._find(id); return i < 0 ? null : this._tabs[i].panel; }
    widgetOf(id) { var i = this._find(id); return i < 0 ? null : this._tabs[i].widget; }
    tabPaneOf(id) { var i = this._find(id); return i < 0 ? null : (this._tabs[i].tabPane || null); }
    getState() { return PaneEvents.state(this); }
    // ── The member: the keys while the pane holds them ────────────────────
    /**
     * THE PANE'S KEYS ARE SWAPPABLE, and this is one of the two doors they
     * come in by: the pane HOLDS them, nothing is natively focused, and the
     * steward has routed the key here. Each scheme is asked in the order it
     * was given and the first to take it has it.
     */
    keyDown(ev) { return PaneSchemes.keyDown(this._schemes, this, ev); }

    /**
     * The other door: something BELOW this pane has the browser's focus — a
     * field in a tab's widget — and a chord got past it and up the party to
     * here. The pane is not the holder and has no state saying so; the steward
     * worked out the chain from where the key was pressed, which is why
     * nothing here has to be remembered.
     *
     * A scheme that means to behave like a browser answers here as well as on
     * the bar, because moving between tabs while you are typing is the whole
     * of what it is imitating. The container's scheme answers false: an arrow
     * belongs to the field.
     */
    chord(ev) { return PaneSchemes.chord(this._schemes, this, ev); }

    /** The schemes this pane answers, live: a scheme, a list of them, or nothing for the container's own. */
    keys(schemes) { if (arguments.length === 0) return this._schemes.slice(); this._schemes = PaneSchemes.of(schemes); return this; }
    /** The strip's ground, right-clicked: the page's own kind, if it named one; PaneMenus says. */
    menuByGround(at) { return PaneMenus.onGround(this, at); }
    /** The active tab's menu, at its chip, when the page offers menus; true when the steward took it. */
    bar() { return this._strip.el; }
    barGround(target) { return this._strip.ground(target); }
    menuByKey() { return PaneMenus.byKey(this); }
    /** The active tab asked to detach and float: DetachRequested, for a holder with a desk. */
    requestDetach() { if (this._activeId !== null) this._fire(PaneEvents.DetachRequested(this.slotId, this._activeId)); }
    /** The pane yields the keys, up the tree: the first ancestor that would hold, else no one. */
    yieldKeys() { Keys.yield(this.focus.owner); }
    /** A yield from a widget inside: the pane holds — unless its scheme says the pane is a road and not a place, and then it is passed above. */
    wouldHold() { return PaneSchemes.keeps(this._schemes, this); }
    /** Asked by the walk about a member of the dock's branch: PaneKeys says which. */
    wouldOffer(m) { return PaneKeys.wouldOffer(this, m); }
    /** Where the keys are, said on the active chip and the bar; the frame's mark is the steward's. */
    granted(by) { this._holds = true; this._keys(); if (by !== "native" && !PaneSchemes.keeps(this._schemes, this) && this._activeId !== null) this.land(this._activeId); }
    taken() { this._holds = false; this._keys(); }
    /** Landed IN a tab: the widget now showing takes the keys — and the steward, whatever else had the browser's focus. A scheme that is a road rather than a place asks for this. */
    land(id) { var w = this.widgetOf(id); if (w && w.activate) w.activate(); }

    /**
     * Told by the steward that the focus is inside the pane — in a tab's widget: the bar is not where the work is —
     * and where: the tab it is in is shown, whoever put it there, a press or a script.
     */
    within(on, at) {
        this._inside = !!on;
        if (on && at && at.state !== "away" && at.root) for (var i = 0; i < this._tabs.length; i++) if (this._tabs[i].panel.contains(at.root)) { this.switchTab(this._tabs[i].id); break; }
        this._keys();
    }
    _keys() {
        var v = PaneKeys.keysState(this);
        this._strip.keys(this.chipOf(this._activeId === null ? -1 : this._find(this._activeId)), v);
        // THE BAR IS LIT WHILE THE WORK IS IN HERE — the pane holding the keys, or a widget in one of its tabs
        // holding them. The pane is the one that knows: the steward tells it both, and it is already writing where
        // the keys are on its own word and marking the active chip. This is the same fact wearing a third face, so
        // it belongs on the same line. Nobody outside has to watch the pane to learn what the pane was told.
        this._strip.current(this._holds || this._inside);
    }

    dispose() {
        if (this._disposed) return;
        if (this._tabs.length) throw new Error("[MultiTabPane] slot '" + this.slotId + "' still holds tab-panes, which are its desk's: let them go first");
        this._disposed = true;
        this._dropPicker();
        if (this._offKeys) { this._offKeys(); this._offKeys = null; }
        if (this.el.parentNode) this.el.parentNode.removeChild(this.el);
        if (this.focus.owner.in) this.focus.owner.leave();   // the dock's branch dissolved with it
        this._branch.dissolve();
    }
}
