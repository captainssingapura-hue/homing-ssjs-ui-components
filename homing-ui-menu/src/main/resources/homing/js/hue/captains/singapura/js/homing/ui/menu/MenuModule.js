// =============================================================================
// Menu — what the three levels of a context menu share (owner, 23 Oct): the
// context menu, level 0, holds a level-1 menu for each of its rows that opens
// rows; a level-1 menu a level-2 menu for each of its; a level-2 menu's rows open
// none. Never made on its own: each level is, with its own fixed type and
// binding, and the level below it handed in. A composed component on a branch of
// its own: its rows made once from its part of the kind's tree, each the house's
// menu item - plain, or one checked or not - cast as a choice; the house's
// separator where the section changes; the menu below each row that opens rows.
//
// Its cursor is the row that is Pointed, one at most. The pointer onto a usable
// row points at it; a row that opens rows opens them beside it once the pointer
// has rested there for the pause (Windows' own, 400ms), and another row of the
// same menu, rested on as long, closes them. The pointer leaving the menu lets
// the cursor go, but onto the row whose rows are open (owner, 23 Oct: Windows
// first). An off row never takes the cursor: onto it, none is pointed. The keys
// go to the deepest menu with a cursor: up and down, Home and End through its
// usable rows, wrapping; right, Enter or Space on a row that opens rows opens
// them, its first usable row pointed; left closes the deepest; Enter or Space
// on another row presses it. A press is a pick, counted as the menu's own
// (MenuPickControl), by the row - its own rows' and those of the menus it holds,
// passed on - and none for a row that opens rows: what a pick does, its opener
// decides. What depends on the object it is open for - off, checked, hidden - is
// bound, and kept until bound again.
//
//   class ContextMenu extends Menu { constructor(branch, fixed, place) {
//       super(branch, fixed, FixedContextMenu, place ?? manageContextMenu, [L1Menu, FixedL1Menu]); } }
//   menu.hold(nodes, label)   once: { id, label, icon?, hint?, section?, checkable?, nodes? }
//   menu.bind(object, state?)   state(id, object) → { disabled?, checked?, hidden? }
//   menu.key(ev) → whether it took the key   menu.contains(node)   menu.mount(host)   still, every level open
//   menu.picks   menu.onChange(StateMenuPickControl, listener)
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _menuOwner = Object.freeze({ toString: () => "menu" });

/** A row pressed, as its menu hears it: one more pick of it, or its rows opened. */
class RowPicked {
    constructor(menu, row) { this.menu = menu; this.row = row; Object.freeze(this); }
    canHandle(on) { return on === StatePickControl; }
    handle(change) { this.menu._picked(this.row); }
}

/** The pointer onto a row, as its menu hears it: its own setter's pointing is the menu's already. */
class RowPointed {
    constructor(menu, row) { this.menu = menu; this.row = row; Object.freeze(this); }
    canHandle(on) { return on === StatePointed; }
    handle(change) { if (change.after === true && this.menu._cursor !== this.row) this.menu._pointerOn(this.row); }
}

/** A pick in a menu a row opens: passed on, as the same row's. */
class RowsPicked {
    constructor(menu) { this.menu = menu; Object.freeze(this); }
    canHandle(on) { return on === StateMenuPickControl; }
    handle(change) { this.menu._passOn(change.after); }
}

class Menu extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        picks: Object.freeze({ rest: StateMenuPickControl.rest, state: StateMenuPickControl })   // by the row, passed on: no class
    });
    static BEHAVIOR = MenuBehavior;
    /** How long the pointer rests on a row before its rows open, or another's close: Windows' own. */
    static PAUSE = 400;

    constructor(branch, fixed, Fixed, place, below) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[Menu] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_menuOwner);
        super(branch.elementMaker("frame"), fixed, Fixed, place);
        this.branch = branch;
        this._below = below;        // the level below - its class and its fixed type - or none, at the last level
        this._rows = [];
        this._counts = new Map();   // each row's id, its own and its menus', to its picks: none for a row that opens rows
        this._cursor = null;        // the row its cursor is on
        this._open = null;          // the row whose rows are open beside it
        this._timer = null;
        this._still = false;
        css.addClass(this.el, cm_frame);
        this.el.setAttribute("role", "menu");
        this.el.setAttribute("tabindex", "-1");
        this.root = this.el;
    }

    // ── made once ─────────────────────────────────────────────────────────
    hold(nodes, label) {
        if (this._rows.length) throw new Error("[Menu] holds its rows already: they are made once");
        this.el.setAttribute("aria-label", label);
        var divided = MenuTree.divided(nodes);
        for (var i = 0; i < divided.length; i++) {
            if (divided[i].divider) {   // the house's separator, cast as the break between sections
                var line = new Separator(this.branch.elementMaker("break-" + i), new FixedSeparator(), this._place(FixedSeparator, RoleBreak));
                css.addClass(line.el, cm_separator);
                this.el.appendChild(line.el);
            }
            this._rows.push(this._row(divided[i].node));
        }
    }
    _row(node) {
        var name = "row-" + node.id.replace(/[^A-Za-z0-9_-]/g, "_"), opens = !!(node.nodes && node.nodes.length);
        if (opens && !this._below) throw new Error("[Menu] " + node.id + " is at the last level and lists rows");
        var Fixed = node.checkable ? FixedCheckMenuItem : FixedPlainMenuItem;
        var leading = node.icon == null ? null : Menu._icon(Fixed, node.icon), hint = node.hint ? new FixedCaption() : null;
        var item = node.checkable ? new CheckMenuItem(this.branch.createBranch(name), new FixedCheckMenuItem(leading, hint), this._place(Fixed, RoleChoice))
                                  : new PlainMenuItem(this.branch.createBranch(name), new FixedPlainMenuItem(leading, hint, opens ? new FixedDiscloseIcon() : null), this._place(Fixed, RoleChoice));
        item.label = node.label;
        if (hint !== null) item.hint = node.hint;
        item.el.setAttribute("data-id", node.id);
        this.el.appendChild(item.el);
        var row = { id: node.id, item: item, sub: null, hidden: false };
        this._count(node.id, opens ? null : 0);
        if (opens) {
            var Below = this._below[0], FixedBelow = this._below[1];
            row.sub = new Below(this.branch.createBranch(name + "-rows"), new FixedBelow(), this._place(FixedBelow, RolePopup));
            row.sub.hold(node.nodes, node.label);
            this._passOn(row.sub.picks);
            row.sub.onChange(StateMenuPickControl, new RowsPicked(this));
        }
        item.onChange(StatePickControl, new RowPicked(this, row));
        item.onChange(StatePointed, new RowPointed(this, row));
        return row;
    }
    /** A row's icon by its word, as the item's fixed type lists its players: { icon: "rotate-icon" }. */
    static _icon(Fixed, word) {
        var P = Fixed.FIELDS.find(f => f.name === "leading").players().find(p => p.COMPONENT.token === word);
        if (!P) throw new Error("[Menu] no icon " + word + " leads a menu's row");
        return new P();
    }

    // ── bound to an object ────────────────────────────────────────────────
    bind(object, state) {
        for (var row of this._rows) {
            var s = (typeof state === "function" && state(row.id, object)) || {};
            row.hidden = !!s.hidden;
            css.toggleClass(row.item.el, cm_item_hidden, row.hidden);
            row.item.enabled = !s.disabled;
            if (row.item instanceof CheckMenuItem) row.item.checked = !!s.checked;
            else if (s.checked != null) throw new TypeError("[Menu] " + row.id + " is no row that can be checked: its kind says which can");
            if (row.sub) row.sub.bind(object, state);
        }
        this._reset();
    }
    /** At rest: nothing open, no cursor, nothing waiting. */
    _reset() { this._wait(null); this._closeRows(); this._point(null); }

    // ── the cursor ────────────────────────────────────────────────────────
    _point(row) {
        if (this._cursor === row) return;
        var was = this._cursor;
        this._cursor = row;
        if (was && was.item.pointed) was.item.pointed = false;
        if (row && !row.item.pointed) row.item.pointed = true;
    }
    _usable(row) { return !row.hidden && row.item.enabled; }
    _move(dir, fromEnd) {
        var from = fromEnd || !this._cursor ? -1 : this._rows.indexOf(this._cursor);
        var i = MenuGeometry.step(this._rows.map(r => this._usable(r)), from, dir);
        if (i < 0) return;
        this._wait(null);
        if (this._open !== this._rows[i]) this._closeRows();
        this._point(this._rows[i]);
    }
    _pointerOn(row) {
        if (this._still) return;
        this._point(row);
        if (this._open === row) { this._wait(null); return; }
        if (row.sub || this._open) this._wait(() => { this._closeRows(); if (row.sub) this._openRows(row); });
    }
    /** The pointer over what holds no cursor - an off row: none pointed, and the rows open closed after the pause. */
    _pointerOff() { if (this._still) return; this._point(null); if (this._open) this._wait(() => this._closeRows()); }
    /** The pointer gone from it: the cursor let go - onto the row whose rows are open, where one is. */
    _left() { if (this._still) return; this._wait(null); this._point(this._open); }
    _wait(then) {
        if (this._timer !== null) { clearTimeout(this._timer); this._timer = null; }
        if (then) this._timer = setTimeout(() => { this._timer = null; then(); }, Menu.PAUSE);
    }

    // ── the rows beside a row ─────────────────────────────────────────────
    _openRows(row) {
        if (this._open === row) return;
        this._closeRows();
        var layer = this.el.parentNode;
        if (!layer) return;
        layer.appendChild(row.sub.el);
        var a = row.item.el.getBoundingClientRect(), f = this.el.getBoundingClientRect(), r = row.sub.el.getBoundingClientRect();
        var first = row.sub._rows.length ? row.sub._rows[0].item.el.getBoundingClientRect().top - r.top : 0;
        var p = MenuGeometry.beside({ left: f.left, top: a.top, right: f.right, bottom: a.bottom }, { w: r.width, h: r.height },
                                    { w: window.innerWidth, h: window.innerHeight }, first);
        row.sub.el.style.setProperty("--cm-x", p.x + "px");
        row.sub.el.style.setProperty("--cm-y", p.y + "px");
        row.item.expanded = true;
        this._open = row;
    }
    _closeRows() {
        var row = this._open;
        if (!row) return;
        this._open = null;
        row.sub._reset();
        row.item.expanded = false;
        if (row.sub.el.parentNode) row.sub.el.parentNode.removeChild(row.sub.el);
    }
    /** Into a row's rows, by the keys: open at once, its first usable row pointed. */
    _enter(row) { this._wait(null); this._openRows(row); row.sub._move(1, true); }

    // ── its picks ─────────────────────────────────────────────────────────
    _picked(row) {
        if (this._still || !this._usable(row)) return;
        if (row.sub) { this._wait(null); this._openRows(row); return; }
        this._count(row.id, this._counts.get(row.id) + 1);
    }
    _count(id, n) {
        this._counts.set(id, n);
        var view = {};
        this._counts.forEach((c, k) => { view[k] = c; });
        this._change("picks", Object.freeze(view));
    }
    _passOn(counts) { for (var id in counts) if (this._counts.get(id) !== counts[id]) this._count(id, counts[id]); }
    /** How many times each row was picked, by its id: its own and those of the menus it holds; none for a row that opens rows. */
    get picks() { return this._get("picks"); }

    // ── the keys, given by its opener ─────────────────────────────────────
    key(ev) {
        var deeper = this._open && this._open.sub._cursor !== null ? this._open.sub : null;
        if (deeper) {
            if (deeper.key(ev)) return true;
            if (ev.key === "ArrowLeft") { var back = this._open; this._closeRows(); this._point(back); return true; }
            return false;
        }
        var c = this._cursor;
        switch (ev.key) {
            case "ArrowDown": this._move(1, false); return true;
            case "ArrowUp": this._move(-1, false); return true;
            case "Home": this._move(1, true); return true;
            case "End": this._move(-1, true); return true;
            case "ArrowRight": if (c && c.sub) this._enter(c); return true;
            case "Enter": case " ": if (c) { if (c.sub) this._enter(c); else c.item.el.click(); } return true;
            default: return false;   // left: the menu holding it closes it
        }
    }

    // ── what it shows ─────────────────────────────────────────────────────
    contains(node) { return !!node && (this.el.contains(node) || (this._open !== null && this._open.sub.contains(node))); }
    /** Still, in a host: every level beside the others, each row that opens rows shown open; nothing picked, nothing moves. */
    mount(host) {
        this._still = true;
        css.addClass(this.el, cm_frame_static);
        host.appendChild(this.el);
        for (var row of this._rows) if (row.sub) { row.item.expanded = true; row.item.pointed = true; row.sub.mount(host); }
    }
    /** It and everything on its branch go together. */
    dispose() { this._reset(); if (this.el.parentNode) this.el.parentNode.removeChild(this.el); this.branch.dissolve(); }
}
