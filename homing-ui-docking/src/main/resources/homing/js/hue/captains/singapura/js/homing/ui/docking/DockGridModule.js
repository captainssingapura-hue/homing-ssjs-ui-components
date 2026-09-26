// =============================================================================
// DockGrid — a desk's docks laid out in a split grid. A REGION IS A CELL OF THE
// GRID AND A DOCK IN IT, and nothing sits between them: no panel, no frame.
// The grid owns every line and never knows what a cell holds; a dock holds
// tabs and never knows where it sits; the desk owns the tabs and moves them.
// What the regions are, and what parting and merging them does, is said here
// once, for every page that holds a workspace: the gallery's docking page and
// the workspace are the same thing built from it (RFC 0066 E3, the workspace
// detour).
//
//   new DockGrid(branch, { host, desk, layout?, menus?, dock?, chooseRegion?, onEvent?,
//                          minCellPx?, seam?, thickness? })
//     host     a flex box; the grid is its item and fills it
//     desk     the Desk, the whole: every dock is added to it, and taken off it
//              when its region goes. The holder's, made and disposed by it
//     layout   the grid's, SplitGridTree's; one cell, "main", unless said
//     menus    the page's ContextMenuSteward. A dock's tab bar, on its own ground,
//              offers DockGrid.MENU - split beside or below, merge the region into
//              one across a splitter of its own, close it - and the picks are
//              handled here
//     dock     what every dock is made with besides what the grid gives it (its
//              host, slot, menus, the strip's menu, the sink, its focus name):
//              addable, keys, …
//     chooseRegion(from, regions, pick)   asked by "Merge into…" for the region the
//              tabs go to; pick(region) merges and answers the plan. Without it,
//              that row is not offered
//     onEvent  the grid's events and every dock's, on one sink
//     minCellPx, seam, thickness   the grid's: 160, on, 1 unless said
//
//   dg.grid .desk
//   dg.regions()          → [{ id, dock }], in the order they were made
//   dg.region(id)  dg.regionOf(dock)
//   dg.part(id, side)     → the new region: an empty dock in a cell of its own on
//                           the left, right, top or bottom of that one
//   dg.merge(id, intoId)  → PaneMerge's plan. Ok: every tab of the region to the
//                           one named, in order, its room where the grid can put
//                           it, and the region gone. Not: nothing moved, and the
//                           plan says why. The last region stays
//   dg.close(id)          → the merge into the region the room would go to
//   dg.across(id)         → [{ way, region }]: the regions across a splitter of its
//                           own - left, right, up, down - at most two
//   dg.heirOf(id)         → the region that gains the room if this one goes
//   dg.dispose()          the docks, empty, then the grid; dispose the desk first
//
// ONE OPERATION, TWO DESTINATIONS. A merge's tabs go to the region named — any
// region, since moving tabs asks nothing of the geometry — and its room goes
// where the grid can put it: the whole of it to that same region when they
// share a splitter of their own, else to the neighbour holding it. It is all or
// nothing: the plan is made from the two docks' tabs before one tab moves, so a
// merge cannot stop half way and strand a widget.
// =============================================================================

const _dockGridOwner = Object.freeze({ toString: () => "dockGrid" });

/** A splitter's side and axis, said as the way across it. */
function _way(s) { return s.axis === "horizontal" ? (s.side === "before" ? "left" : "right") : (s.side === "before" ? "up" : "down"); }

class DockGrid {
    /** The strip-ground menu's kind: SplitMenu, declared by the component that answers it. */
    static MENU = "split";

    constructor(branch, opts) {
        var o = opts || {}, self = this;
        if (!branch) throw new Error("[DockGrid] a branch of its own is required");
        if (!o.host) throw new Error("[DockGrid] opts.host is required");
        if (!o.desk) throw new Error("[DockGrid] opts.desk is required: the docks are its");
        branch.activate(_dockGridOwner);
        this.branch = branch;
        this.desk = o.desk;
        this._dockOpts = o.dock || {};
        this._menus = o.menus || null;
        this._choose = typeof o.chooseRegion === "function" ? o.chooseRegion : null;
        this._sink = typeof o.onEvent === "function" ? o.onEvent : null;
        this._regions = [];
        this._made = 0;   // the docks' names on the branch and the party: a count, never a cell's id
        this.grid = new SplitGrid(branch.createBranch("grid"), {
            host: o.host, layout: o.layout || { kind: "cell", id: "main" },
            minCellPx: o.minCellPx == null ? 160 : o.minCellPx, seam: o.seam !== false, thickness: o.thickness == null ? 1 : o.thickness,
            onEvent: function (ev) { self._fire(ev); }
        });
        this.grid.cells().forEach(function (id) { self._region(id); });
        if (this._menus) this._handle(this._menus);
    }

    _fire(ev) {
        if (!this._sink) return;
        try { this._sink(ev); } catch (e) { console.error("[DockGrid] onEvent threw on " + ev.kind + ":", e); }
    }

    /** A region: a dock in that cell, on the desk. */
    _region(id) {
        var self = this, n = ++this._made;
        var dock = new MultiTabPane(this.branch.createBranch("dock-" + n), Object.assign({}, this._dockOpts, {
            host: this.grid.cell(id), slotId: id, menus: this._menus, stripMenu: this._menus ? DockGrid.MENU : null,
            focusName: "dock-" + n, onEvent: function (ev) { self._fire(ev); } }));
        this.desk.addDock(dock);
        var r = Object.freeze({ id: id, dock: dock });
        this._regions.push(r);
        return r;
    }

    regions() { return this._regions.slice(); }
    region(id) { for (var i = 0; i < this._regions.length; i++) if (this._regions[i].id === id) return this._regions[i]; return null; }
    regionOf(dock) { for (var i = 0; i < this._regions.length; i++) if (this._regions[i].dock === dock) return this._regions[i]; return null; }

    part(id, side) {
        if (!this.region(id)) throw new Error("[DockGrid] no region '" + id + "'");
        return this._region(this.grid.subdivide(id, side));
    }

    across(id) {
        var self = this;
        return (this.grid.splitters(id) || []).map(function (s) { return { way: _way(s), region: self.region(s.id) }; })
                                              .filter(function (x) { return x.region; });
    }

    heirOf(id) {
        var ids = this.grid.heirs(id) || [];
        for (var i = 0; i < this._regions.length; i++) if (ids.indexOf(this._regions[i].id) >= 0) return this._regions[i];
        return null;
    }

    merge(id, intoId) {
        var r = this.region(id), to = this.region(intoId);
        if (!r || !to || r === to) return { ok: false, reason: "none", says: "no such region to merge into" };
        var plan = PaneMerge.plan(r.dock.tabs(), to.dock.tabs());
        if (!plan.ok) return plan;
        var desk = this.desk;
        plan.ids.forEach(function (tabId) { desk.move(r.dock.tabPaneOf(tabId), to.dock); });
        desk.removeDock(r.dock);
        r.dock.dispose();
        this._regions.splice(this._regions.indexOf(r), 1);
        this.grid.remove(r.id, to.id);
        return plan;
    }

    close(id) {
        var heir = this.heirOf(id);
        return heir ? this.merge(id, heir.id) : { ok: false, reason: "last", says: "the last region stays" };
    }

    /** The region across a splitter of this one's own, that way; or null. */
    _towards(r, way) { var n = this.across(r.id).filter(function (x) { return x.way === way; })[0]; return n ? n.region : null; }

    /**
     * The tab bar's own ground, right-clicked: the menu about the room the dock sits in. A direction with no
     * region across a splitter of this one's own is not offered at all; one whose dock cannot take the tabs is
     * offered and refused, so the reason can be read rather than guessed at.
     */
    _handle(menus) {
        var self = this;
        menus.handle(DockGrid.MENU, {
            pick: function (id, o) {
                var r = self.regionOf(o.pane);
                if (!r) return;
                if (id === "beside") self.part(r.id, "right");
                else if (id === "below") self.part(r.id, "bottom");
                else if (id === "close") self.close(r.id);
                else if (id === "merge-into") {
                    if (self._choose) self._choose(r, self._regions.filter(function (x) { return x !== r; }), function (to) { return self.merge(r.id, to.id); });
                }
                else if (id.indexOf("merge-") === 0) { var to = self._towards(r, id.slice(6)); if (to) self.merge(r.id, to.id); }
            },
            state: function (id, o) {
                var r = self.regionOf(o.pane), to;
                if (id === "close") return { disabled: !r || self._regions.length < 2 };
                if (id === "merge-into") return { hidden: !r || !self._choose, disabled: self._regions.length < 2 };
                if (id.indexOf("merge-") !== 0) return { disabled: !r };   // every row is about a region, and a dock afloat is not one
                to = r && self._towards(r, id.slice(6));
                return { hidden: !to, disabled: !!to && !PaneMerge.plan(r.dock.tabs(), to.dock.tabs()).ok };
            }
        });
    }

    dispose() {
        var desk = this.desk;
        this._regions.forEach(function (r) { try { desk.removeDock(r.dock); } catch (e) {} r.dock.dispose(); });
        this._regions = [];
        this.grid.dispose();
        try { this.branch.dissolve(); } catch (e) {}
    }
}
