// =============================================================================
// SplitGrid — a tree of rows and columns of cells sharing their space by
// ratio, the tracks, with a line where two cells meet. A container in the
// relation grid's sense: the owner mints what goes in a cell, the grid
// arranges the cells and reports the arrangement, and never knows what a cell
// holds. A branch component: the caller makes a sub-branch for it and hands
// it in; dispose() dissolves it.
//
//   new SplitGrid(branch, { host, layout, resizable?, minCellPx?, onEvent? })
//     branch:    the grid's own, handed unactivated
//     host:      a flex box; the grid is its item and fills it.
//     layout:    SplitGridTree's — { kind: "cell", id } | { kind: "split", orientation, children: [{ node, ratio? }] }
//     resizable: sizing applied (RFC 0068, appendix C), by construction: each
//                split's tracks a rail, and every line between two cells the
//                rail's boundary, the hand moving it. Not resizable - the
//                default - the grid lays its cells out in the proportions it
//                was given, and no line of it moves.
//     minCellPx: the least a cell may be, 40 unless said; a part holding a
//                split is as least as the cells across it.
//
//   grid.el
//   grid.resizable()           → whether sizing is applied
//   grid.box()                 → { w, h }: the grid's box in px, for a mirror to reflect
//   grid.cell(id)              → the cell's element, the box the owner fills (a
//                                flex column; what is put in it is its item)
//   grid.cells()               → the ids, in tree order
//   grid.layout()              → the tree with the tracks as they are now
//   grid.setRatios(path, ratios)        re-share one split; reports TracksChanged.
//                                Sizing's: refused by a grid that is not resizable
//   grid.subdivide(id, side, newId?)    → the new cell's id: an empty cell beside
//                                the one named, on its left, right, top or
//                                bottom, each taking half the room; a sibling in
//                                the same row or column when the orientation
//                                matches, else the cell becomes a split of the
//                                two. Unnamed, the grid names it, never with an
//                                id a cell of this grid has had. Reports
//                                Subdivided
//   grid.splitters(id)         → the cells across a splitter of this one's own:
//                                [ { side, axis, id } ]. At most two — the panes
//                                its room can go to whole, one per side.
//   grid.heirs(id, toward?)    → the cells that gain the room if that one goes:
//                                the pane across the splitter when the cell
//                                named is it, else the group beside it.
//   grid.remove(id, toward?)   → the cell's element, detached, for the owner to
//                                move its content on; a split left with one
//                                gives way to it; the last cell cannot go.
//                                Its room goes toward the cell named, if one
//                                is: the whole of it to that cell when they
//                                share a splitter, else to the neighbour
//                                holding it; unnamed, to the one beside it.
//                                Reports Removed, with the toward it was given
//   grid.dispose()
//
// The cells are the grid's constant: a cell's element is minted once and kept
// through every re-arrangement, so what the owner put in it stays put; the
// splits, the children and the lines are re-minted around them.
//
// AN ID IS THE OWNER'S NAME; A NAME ON THE BRANCH IS THE GRID'S. The owner
// keys on a cell's id, logs by it and keeps it; the element's name on the
// grid's branch is only the grid's, and is from a count. The branch holds a
// name until it dissolves, so a name made from the id would refuse a cell
// that comes back under the id of one gone, or two ids of one safe spelling.
// And the ids the grid hands out are never ones it has handed out or been
// given before: an owner never meets a gone cell's id meaning another. Each
// track is a custom property on the child, --sg-ratio, read by its class, and
// its least --sg-least; nothing is positioned by hand.
//
// THE LINE IS THE GRID'S PART, IN THE HOUSE'S LOOK: a boundary line, cast as
// a splitter where the grid is resizable and as a seam where it is not - as
// thick as the theme says and in its colour, the grid choosing neither. Its
// cursor and the reach of the hand past it are sizing's, which the handle
// brings, so the line wears its look but for those. Resizable, each split
// keeps a rail, read from its tracks by the one rounding - its shares whole
// millionths - and the handle on each line moves the two either side of it
// over the room the shares divide, each kept at its least, the change
// reported once, on release. The grid's own edits still share the room as
// its tree does; the rails are read again after each.
// `css` is injected with the styles import.
// =============================================================================

const _gridOwner = Object.freeze({ toString: () => "splitGrid" });

class SplitGrid {
    constructor(branch, opts) {
        if (!branch) throw new Error("[SplitGrid] a branch of its own is required");
        if (!opts || !opts.host) throw new Error("[SplitGrid] opts.host is required");
        branch.activate(_gridOwner);
        this._branch = branch;
        this._minPx = opts.minCellPx == null ? 40 : Math.max(0, opts.minCellPx | 0);
        this._resizable = !!opts.resizable;
        this._sink = typeof opts.onEvent === "function" ? opts.onEvent : null;
        this._cells = new Map();         // id → the cell element, kept for the grid's life
        this._splits = new Map();        // path → { el, orientation, children: [{ el, node }], lines: [el] }
        this._rails = new Map();         // path → the split's rail, while the grid is resizable
        this._handles = [];              // the sizing handles on the lines, re-armed with every arrangement
        this._tree = SplitGridTree.validate(opts.layout);
        this._held = new Set(SplitGridTree.cells(this._tree));   // every id a cell of this grid has had
        this._seq = this._held.size;     // the count a fresh id is made from
        this._minted = 0;                // the count the cells' elements are named from
        this._n = 0;
        this._arrangement = null;        // the sub-branch the current splits, children and lines are minted on

        var root = branch.createElement("root", "div");
        css.addClass(root, sg_root);
        root.style.setProperty("--sg-min", this._minPx + "px");
        this.el = root;
        this._arrange();
        opts.host.appendChild(root);
        this._handles.forEach(function (h) { h.reflect(); });   // where each line stands, now that there is a room to stand in
    }

    // ── Reporting ─────────────────────────────────────────────────────────
    _fire(ev) {
        if (!this._sink) return;
        try { this._sink(ev); }
        catch (e) { console.error("[SplitGrid] onEvent threw on " + ev.kind + ":", e); }
    }

    // ── The arrangement: the tree rendered around the cells ───────────────
    _cellEl(id) {
        var el = this._cells.get(id);
        if (!el) {
            el = this._branch.createElement("cell-" + (++this._minted), "div");
            css.addClass(el, sg_cell);
            el.setAttribute("data-cell", id);
            this._cells.set(id, el);
        }
        return el;
    }
    _arrange() {
        var old = this._arrangement;
        this._handles.forEach(function (h) { h.dispose(); });
        this._handles = [];
        this._arrangement = this._branch.createBranch("arrangement-" + (++this._n));
        this._arrangement.activate(_gridOwner);
        this._splits.clear();
        this._rails.clear();
        var fresh = this._render(this._tree, "");
        while (this.el.firstChild) this.el.removeChild(this.el.firstChild);
        this.el.appendChild(fresh);
        this._handles.forEach(function (h) { h.reflect(); });
        if (old) { try { old.dissolve(); } catch (e) {} }
    }
    _render(node, path) {
        var branch = this._arrangement;
        var name = path === "" ? "root" : path.replace(/\//g, "_");
        if (node.kind === "cell") return this._cellEl(node.id);
        var horizontal = node.orientation === "horizontal";
        if (this._resizable) {   // the tracks read as a rail: whole millionths, by the one rounding
            var rail = Rail.of(node.children.map(function (c) { return c.ratio; }));
            node.children.forEach(function (c, i) { c.ratio = rail.shares[i] / Rail.WHOLE; });
            this._rails.set(path, rail);
        }
        var el = branch.createElement("split-" + name, "div");
        css.addClass(el, sg_split, horizontal ? sg_split_h : sg_split_v);
        var entry = { el: el, orientation: node.orientation, children: [], lines: [] };
        for (var i = 0; i < node.children.length; i++) {
            if (i > 0) {
                // the house's boundary line, cast as a splitter where the grid is resizable and as a seam where it is not:
                // upright between cells side by side; its cursor and its reach the handle's
                var boundary = new BoundaryLine(branch.elementMaker("line-" + name + "-" + i), { orientation: horizontal ? "vertical" : "horizontal" },
                                                this._resizable ? ROLE.SPLITTER : ROLE.SEAM);
                var line = boundary.el;
                css.addClass(line, sg_line);
                if (!this._resizable) css.addClass(line, sg_line_fixed);
                if (this._resizable) this._handles.push(this._handle(boundary, path, i - 1, horizontal));
                entry.lines.push(line);
                el.appendChild(line);
            }
            var child = branch.createElement("child-" + name + "-" + i, "div");
            css.addClass(child, sg_child, horizontal ? sg_child_h : sg_child_v);
            child.style.setProperty("--sg-ratio", String(node.children[i].ratio));
            child.style.setProperty("--sg-least", this._least(node.children[i].node, horizontal, 0) + "px");
            child.appendChild(this._render(node.children[i].node, path === "" ? String(i) : path + "/" + i));
            entry.children.push({ el: child, node: node.children[i] });
            el.appendChild(child);
        }
        this._splits.set(path, entry);
        return el;
    }

    /** Sizing's handle on a line: the rail its split keeps, measured as laid out, laid out again as it moves. */
    _handle(boundary, path, index, horizontal) {
        var self = this;
        return new SizingHandle(boundary.el, {
            onGrip: function (on) { boundary.dragging = on; },   // the line in the hand from the press to the release
            axis: horizontal ? "horizontal" : "vertical",
            index: index,
            rail: function () { return self._rails.get(path); },
            measure: function () { return self._measure(path); },
            onMove: function (rail) { self._lay(path, rail); },
            onDone: function (rail) {
                self._lay(path, rail);
                self._fire(SplitGridEvents.TracksChanged(path, self._ratiosOf(path)));
            }
        });
    }

    /** The room a split's shares divide - its length less its lines - and each part's least, as laid out now. */
    _measure(path) {
        var entry = this._splits.get(path), horizontal = entry.orientation === "horizontal";
        function along(el) { var r = el.getBoundingClientRect(); return horizontal ? r.width : r.height; }
        var lines = 0;
        entry.lines.forEach(function (l) { lines += along(l); });
        var line = entry.lines.length ? lines / entry.lines.length : 0, self = this;
        return { room: Math.max(0, along(entry.el) - lines),
                 least: entry.children.map(function (c) { return self._least(c.node.node, horizontal, line); }) };
    }

    /** The least a part may be along a split: a cell's least; a split along it, its parts and its lines; across it, its largest part's. */
    _least(node, horizontal, line) {
        if (node.kind === "cell") return this._minPx;
        var self = this, along = (node.orientation === "horizontal") === horizontal;
        var parts = node.children.map(function (c) { return self._least(c.node, horizontal, line); });
        if (!along) return Math.max.apply(null, parts);
        return parts.reduce(function (a, b) { return a + b; }, 0) + (parts.length - 1) * line;
    }

    /** A split laid out by its rail: each track its share. */
    _lay(path, rail) {
        var entry = this._splits.get(path);
        this._rails.set(path, rail);
        for (var i = 0; i < entry.children.length; i++) {
            entry.children[i].node.ratio = rail.shares[i] / Rail.WHOLE;
            entry.children[i].el.style.setProperty("--sg-ratio", String(entry.children[i].node.ratio));
        }
        this._handles.forEach(function (h) { h.reflect(); });
    }
    _ratiosOf(path) {
        var entry = this._splits.get(path), out = [];
        for (var i = 0; i < entry.children.length; i++) out.push(entry.children[i].node.ratio);
        return out;
    }

    // ── The surface ───────────────────────────────────────────────────────
    resizable() { return this._resizable; }
    box() { var r = this.el.getBoundingClientRect(); return { w: r.width, h: r.height }; }
    cell(id) { return this._cells.get(id) || null; }
    cells() { return SplitGridTree.cells(this._tree); }
    layout() { return SplitGridTree.copy(this._tree); }

    setRatios(path, ratios) {
        if (!this._resizable) throw new Error("[SplitGrid] not resizable: its proportions are the ones it was given");
        var entry = this._splits.get(path);
        if (!entry) throw new Error("[SplitGrid] no split at '" + path + "'");
        if (!Array.isArray(ratios) || ratios.length !== entry.children.length) throw new Error("[SplitGrid] setRatios: " + entry.children.length + " ratios expected at '" + path + "'");
        for (var i = 0; i < ratios.length; i++) {
            if (typeof ratios[i] !== "number" || !(ratios[i] > 0)) throw new Error("[SplitGrid] setRatios: ratios must be positive numbers");
        }
        this._lay(path, Rail.of(ratios));
        this._fire(SplitGridEvents.TracksChanged(path, this._ratiosOf(path)));
    }

    subdivide(id, side, newId) {
        var fresh = newId == null ? this._freshId() : String(newId);
        this._tree = SplitGridTree.subdivide(this._tree, id, side, fresh);
        this._held.add(fresh);
        this._arrange();
        this._fire(SplitGridEvents.Subdivided(id, fresh, side));
        return fresh;
    }

    splitters(id) { return SplitGridTree.splitters(this._tree, id); }

    heirs(id, toward) { return SplitGridTree.heirs(this._tree, id, toward); }

    remove(id, toward) {
        this._tree = SplitGridTree.remove(this._tree, id, toward);
        var el = this._cells.get(id);
        this._cells.delete(id);
        this._arrange();
        if (el && el.parentNode) el.parentNode.removeChild(el);
        this._fire(SplitGridEvents.Removed(id, toward == null ? null : String(toward)));
        return el || null;
    }

    /** An id no cell of this grid has had, whether the grid named it or the owner did. */
    _freshId() {
        var id;
        do { id = "cell-" + (++this._seq); } while (this._held.has(id));
        return id;
    }

    dispose() {
        this._handles.forEach(function (h) { h.dispose(); });
        this._handles = [];
        if (this.el.parentNode) this.el.parentNode.removeChild(this.el);
        this._cells.clear();
        this._splits.clear();
        this._rails.clear();
        this._branch.dissolve();
    }
}
