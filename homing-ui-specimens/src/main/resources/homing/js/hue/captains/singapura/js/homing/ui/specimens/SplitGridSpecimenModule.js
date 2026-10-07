// =============================================================================
// SplitGridSpecimen — the house's split grid in action: rooms in rows and
// columns, sharing their space by ratio, the lines between them dragged to
// re-share. A room is split beside itself, each half taking half its room;
// a room removed gives its room to the one beside it, and the last room
// cannot go. What each room holds is its owner's and stays put through every
// change. Every re-share, split and removal is said.
//
//   new SplitGridSpecimen(branch, { leaf, say })   leaf: "split-grid"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _splitGridSpecimen = Object.freeze({ toString: () => "splitGridSpecimen" });

class SplitGridSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_splitGridSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        root.appendChild(host);
        var label = function (id) {
            var t = branch.createElement("room-" + id, "p");
            css.addClass(t, sp_text);
            t.textContent = "Room " + id;
            self._grid.cell(id).appendChild(t);
        };
        var shares = function (rs) { return rs.map(function (r) { return Math.round(r * 100) + "%"; }).join(" : "); };
        this._seam = true;
        this._grid = new SplitGrid(branch.createBranch("grid"), {
            host: host, seam: true,
            layout: { kind: "split", orientation: "horizontal", children: [
                { node: { kind: "cell", id: "a" } },
                { node: { kind: "split", orientation: "vertical", children: [{ node: { kind: "cell", id: "b" } }, { node: { kind: "cell", id: "c" } }] } }] },
            onEvent: function (ev) {
                if (ev.kind === "TracksChanged") say("re-shared: " + shares(ev.ratios));
                else if (ev.kind === "Subdivided") { label(ev.newCellId); say("room " + ev.cellId + " split: room " + ev.newCellId + " on its " + ev.side); }
                else if (ev.kind === "Removed") say("room " + ev.cellId + " removed; its room went " + (ev.toward ? "to room " + ev.toward : "to the one beside it"));
            }
        });
        this._grid.cells().forEach(label);

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var b = function (name, text, fn) {
            var x = new ButtonBuilder().label(text).plain().onClick(fn);
            row.appendChild(x.build(branch.createElement(name, x.tag)).el);
        };
        b("split", "Split the first room to the right", function () { self._grid.subdivide(self._grid.cells()[0], "right"); });
        b("remove", "Remove the last room", function () {
            var cells = self._grid.cells();
            try { self._grid.remove(cells[cells.length - 1]); }
            catch (e) { say("refused: " + e.message); }
        });
        b("seam", "Hide the lines", function () {
            self._seam = !self._seam;
            self._grid.seam(self._seam);
            say(self._seam ? "the lines drawn, the grid's own edge with them" : "no lines: the rooms' joints have no presence of their own");
        });
        root.appendChild(row);

        this.root = root;
        say("drag a line between rooms, split a room, remove one - the last cannot go");
    }

    /** A grid varies along no axis. */
    extent(axis, v) {}

    /** It takes no keys. */
    key(ev) { return false; }

    dispose() {
        try { this._grid.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
