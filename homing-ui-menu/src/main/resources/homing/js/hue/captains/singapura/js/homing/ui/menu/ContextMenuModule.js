// =============================================================================
// ContextMenu — one kind's instance: its tree, built once on the branch
// given and kept — a frame per node that has rows, the root's and every
// submenu's — bound to an object while it is shown, and to none after. What
// is open is a PATH: the root, then the submenu of the row the cursor went
// into, then that submenu's, three levels at most, each frame beside its
// row. The steward makes one per kind at the kind's first open and holds
// it; nothing here is minted twice. A branch component: the steward makes
// a sub-branch for it and hands it in.
//
//   new ContextMenu(branch, type, hooks, opts?)
//     type   { kind, nodes }: nodes { id, label, icon?, hint?, section?, nodes? }
//     hooks  { pick(itemId) }
//     opts   { specimen: true } — a static display of the whole tree, every
//            level open beside its row, for a gallery; nothing is picked
//   menu.el                       the root frame, role=menu, focusable
//   menu.bind(object, state?)     state(itemId, object) → { disabled?, checked?, hidden? }
//   menu.unbind()   menu.bound()
//   menu.show(layer, at, viewport, fromKeyboard) → { x, y } where it was placed
//   menu.hide()
//   menu.mount(host)              specimen: the frames into the host, all open
//   menu.contains(node)           a frame on the open path holds the node
//   menu.key(ev) → boolean        arrows, Home, End, Enter, Space, Left, Right
//   menu.dispose()
//
// A row is the house's menu item, cast as a choice of its menu: its glyph,
// its name, its hint and its way into its rows its own. The cursor is the
// row that is Pointed - its look tints it - and moves by MenuGeometry.step
// within the deepest open frame, skipping rows that are off or hidden and
// wrapping; the pointer coming onto a row points at it, and the menu follows.
// A row with rows opens them beside it when pointed at, on Enter and on
// ArrowRight; pointing at another row of the same frame closes them,
// ArrowLeft steps back a level. A row's glyph is its own word, or the check
// when the row is checked; a divider sits where the section changes. Every
// frame wears the menu's casting - lifted over the page as its style casts
// it. A row off (its Enabled false) is faded by its look. The menu's own
// classes only place. `css` is injected with the styles import.
// =============================================================================

const _menuOwner = Object.freeze({ toString: () => "contextMenu" });

/** The pointer onto a row: the cursor there, and its rows open beside it. A pointing by the menu's own setter is the menu's already. */
class MenuRowPointed {
    constructor(menu, row) { this._menu = menu; this._row = row; }
    canHandle(type) { return type === STATE.POINTED.changed; }
    handle(event) { if (event.raw && this._row.item.pointed) this._menu._hover(this._row); }
}

/** A row pressed while usable: picked, or its rows opened. */
class MenuRowPressed {
    constructor(menu, row) { this._menu = menu; this._row = row; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle(event) { if (event.raw && typeof event.raw.preventDefault === "function") event.raw.preventDefault(); this._menu._activate(this._row); }
}

class ContextMenu {
    static LEAF = "context-menu";

    constructor(branch, type, hooks, opts) {
        if (!branch) throw new Error("[ContextMenu] a branch of its own is required");
        branch.activate(_menuOwner);
        this._branch = branch;
        this.kind = type.kind;
        this._hooks = hooks || {};
        this._specimen = !!(opts && opts.specimen);
        this._object = null;
        this._viewport = { w: 0, h: 0 };
        this._path = [];                // the rows whose submenu is open, outermost first
        this._frameLook = new Look(ContextMenu, ROLE.STANDALONE);
        this._root = this._level("frame", type.kind, type.nodes, "", 0);
        this.el = this._root.el;
    }

    // ── built once ────────────────────────────────────────────────────────
    _level(name, label, nodes, prefix, depth) {
        var f = this._branch.createElement(name, "div");
        css.addClass(f, cm_frame);
        this._frameLook.wear(f);
        if (this._specimen) css.addClass(f, cm_frame_static);
        f.setAttribute("role", "menu");
        f.setAttribute("aria-label", label);
        f.setAttribute("tabindex", "-1");
        f.addEventListener("contextmenu", function (e) { e.preventDefault(); });
        var level = { el: f, rows: [], cursor: -1, depth: depth };
        var divided = MenuTree.divided(nodes);
        for (var i = 0; i < divided.length; i++) {
            if (divided[i].divider) {
                // the house's separator, cast as the break between sections: placed by the menu
                var sep = new Separator(this._branch.elementMaker(prefix + "sep-" + i), {}, ROLE.BREAK);
                css.addClass(sep.el, cm_separator);
                f.appendChild(sep.el);
            }
            level.rows.push(this._row(f, divided[i].node, prefix, level));
        }
        return level;
    }
    _row(frame, node, prefix, level) {
        var name = prefix + "row-" + node.id.replace(/[^A-Za-z0-9_-]/g, "_");
        var opens = !!(node.nodes && node.nodes.length);
        // the house's menu item, cast as a choice of its menu: its glyph, its name, its hint and its way on its own; placed by the menu
        var item = new MenuItem(this._branch.createBranch(name), { label: node.label, icon: node.icon || null, hint: node.hint || null,
                                expanded: opens ? false : null }, ROLE.CHOICE);
        item.el.setAttribute("data-id", node.id);
        var row = { id: node.id, item: item, el: item.el, mark: item.mark, icon: node.icon || null, sub: null, disabled: false, hidden: false, level: level };
        if (opens) row.sub = this._level(name + "-sub", node.label, node.nodes, name + "-", level.depth + 1);
        item.setOnPointedChanged(new MenuRowPointed(this, row));
        item.setOnClick(new MenuRowPressed(this, row));
        frame.appendChild(item.el);
        return row;
    }

    // ── bound to an object ────────────────────────────────────────────────
    bind(object, state) {
        this._object = object;
        this._apply(this._root, object, state);
        this._root.cursor = -1;
        this._closeTo(0);
    }
    _apply(level, object, state) {
        for (var i = 0; i < level.rows.length; i++) {
            var r = level.rows[i];
            var s = (typeof state === "function" && state(r.id, object)) || {};
            r.disabled = !!s.disabled;
            r.hidden = !!s.hidden;
            css.toggleClass(r.el, cm_item_hidden, r.hidden);
            r.item.enabled = !r.disabled;
            r.item.checked = s.checked == null ? null : !!s.checked;
            if (s.checked) r.mark.set("check"); else if (r.icon) r.mark.set(r.icon); else r.mark.clear();
            if (r.sub) this._apply(r.sub, object, state);
        }
    }
    unbind() { this._object = null; this._closeTo(0); this._highlight(this._root, -1); }
    bound() { return this._object; }

    // ── shown and hidden ──────────────────────────────────────────────────
    show(layer, at, viewport, fromKeyboard) {
        this._viewport = viewport;
        layer.appendChild(this.el);
        var r = this.el.getBoundingClientRect();
        var p = MenuGeometry.place(at, { w: r.width, h: r.height }, viewport);
        this.el.style.setProperty("--cm-x", p.x + "px");
        this.el.style.setProperty("--cm-y", p.y + "px");
        try { this.el.focus({ preventScroll: true }); } catch (e) {}
        if (fromKeyboard) this._move(1);
        return p;
    }
    hide() {
        this._closeTo(0);
        if (this.el.parentNode) this.el.parentNode.removeChild(this.el);
    }
    /** A specimen: every frame into the host, in tree order, each row with rows shown open and highlighted, so the whole design is on view. */
    mount(host) {
        if (!this._specimen) throw new Error("[ContextMenu] mount is the specimen's; a live menu is shown by the steward");
        var self = this;
        (function place(level) {
            host.appendChild(level.el);
            for (var i = 0; i < level.rows.length; i++) if (level.rows[i].sub) {
                level.rows[i].item.expanded = true;
                level.rows[i].item.pointed = true;
                self._path.push(level.rows[i]);
                place(level.rows[i].sub);
            }
        })(this._root);
    }
    contains(node) {
        if (!node) return false;
        if (this.el.contains(node)) return true;
        for (var i = 0; i < this._path.length; i++) if (this._path[i].sub.el.contains(node)) return true;
        return false;
    }

    // ── the cursor, in the deepest open frame ─────────────────────────────
    _deepest() { return this._path.length ? this._path[this._path.length - 1].sub : this._root; }
    _highlight(level, i) {
        level.cursor = i;
        for (var k = 0; k < level.rows.length; k++) level.rows[k].item.pointed = k === i;
    }
    _enabled(level) {
        var out = [];
        for (var k = 0; k < level.rows.length; k++) out.push(!level.rows[k].disabled && !level.rows[k].hidden);
        return out;
    }
    _move(dir) {
        var level = this._deepest();
        this._highlight(level, MenuGeometry.step(this._enabled(level), level.cursor, dir));
    }
    _end(last) {
        var level = this._deepest();
        this._highlight(level, MenuGeometry.step(this._enabled(level), -1, last ? -1 : 1));
    }
    _current() {
        var level = this._deepest();
        return level.cursor >= 0 ? level.rows[level.cursor] : null;
    }
    _hover(row) {
        if (this._specimen) { this._highlight(row.level, row.level.rows.indexOf(row)); return; }
        this._closeTo(row.level.depth);
        this._highlight(row.level, row.level.rows.indexOf(row));
        if (row.sub && !row.disabled) this._openSub(row);
    }
    _activate(row) {
        if (row.disabled || this._specimen) return;
        if (row.sub) { this._openSub(row); this._move(1); return; }
        if (this._hooks.pick) this._hooks.pick(row.id);
    }

    // ── the path: a submenu beside its row ────────────────────────────────
    _openSub(row) {
        if (this._path.length && this._path[this._path.length - 1] === row) return;
        this._closeTo(row.level.depth);
        var layer = this.el.parentNode;
        if (!layer) return;
        layer.appendChild(row.sub.el);
        var a = row.el.getBoundingClientRect(), r = row.sub.el.getBoundingClientRect(), f = row.level.el.getBoundingClientRect();
        var first = row.sub.rows.length ? row.sub.rows[0] : null, inset = first ? first.el.getBoundingClientRect().top - r.top : 0;
        var p = MenuGeometry.beside({ left: f.left, top: a.top, right: f.right, bottom: a.bottom }, { w: r.width, h: r.height }, this._viewport, inset);
        row.sub.el.style.setProperty("--cm-x", p.x + "px");
        row.sub.el.style.setProperty("--cm-y", p.y + "px");
        row.item.expanded = true;
        this._highlight(row.sub, -1);
        this._path.push(row);
    }
    /** Closes the open path down to a depth: 0 leaves the root alone, 1 keeps the first submenu … */
    _closeTo(depth) {
        if (this._specimen) return;             // a specimen is open at every level, and stays so
        while (this._path.length > depth) {
            var row = this._path.pop();
            row.item.expanded = false;
            if (row.sub.el.parentNode) row.sub.el.parentNode.removeChild(row.sub.el);
        }
    }

    // ── the keys, forwarded by the steward ────────────────────────────────
    key(ev) {
        switch (ev.key) {
            case "ArrowDown": this._move(1); return true;
            case "ArrowUp": this._move(-1); return true;
            case "Home": this._end(false); return true;
            case "End": this._end(true); return true;
            case "ArrowRight": { var r = this._current(); if (r && r.sub && !r.disabled) { this._openSub(r); this._move(1); } return true; }
            case "ArrowLeft": { if (this._path.length) { var back = this._path[this._path.length - 1]; this._closeTo(this._path.length - 1); this._highlight(back.level, back.level.rows.indexOf(back)); } return true; }
            case "Enter": case " ": { var c = this._current(); if (c) this._activate(c); return true; }
            default: return false;
        }
    }

    dispose() { if (!this._specimen) this.hide(); this._branch.dissolve(); }
}
