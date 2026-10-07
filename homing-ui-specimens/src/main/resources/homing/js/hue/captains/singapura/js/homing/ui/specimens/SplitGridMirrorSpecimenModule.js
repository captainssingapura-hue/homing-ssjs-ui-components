// =============================================================================
// SplitGridMirrorSpecimen — the house's split grid mirror in action: a grid's
// shape drawn small, beside the grid it reflects, with a cursor the keys move
// from room to room by the workspace's rule - the arrows, handed on - and a
// press puts it on a room. It never touches the grid: told the grid's layout
// and box, it draws them again, so a room split in the grid is reflected
// after. What the cursor means is its owner's to decide; here, it is said.
//
//   new SplitGridMirrorSpecimen(branch, { leaf, say })   leaf: "split-grid-mirror"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _splitGridMirrorSpecimen = Object.freeze({ toString: () => "splitGridMirrorSpecimen" });

class SplitGridMirrorSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_splitGridMirrorSpecimen);
        this.branch = branch;
        this._alive = true;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var mirrorBox = branch.createElement("mirror-box", "div");
        row.appendChild(mirrorBox);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        root.appendChild(host);
        root.appendChild(row);

        this._mirror = new SplitGridMirror(branch.createBranch("mirror"), {
            host: mirrorBox, scale: 0.35,
            onEvent: function (ev) { if (ev.kind === "CursorMoved") say("the cursor at room " + ev.cellId + " - by " + ev.by); }
        });
        this._grid = new SplitGrid(branch.createBranch("grid"), {
            host: host, seam: true,
            layout: { kind: "split", orientation: "horizontal", children: [
                { node: { kind: "cell", id: "a" } },
                { node: { kind: "split", orientation: "vertical", children: [{ node: { kind: "cell", id: "b" } }, { node: { kind: "cell", id: "c" } }] } }] },
            onEvent: function (ev) { if (ev.kind === "Subdivided" || ev.kind === "Removed") self._reflect(); }
        });
        this._grid.cells().forEach(function (id) {
            var t = branch.createElement("room-" + id, "p");
            css.addClass(t, sp_text);
            t.textContent = "Room " + id;
            self._grid.cell(id).appendChild(t);
        });

        var split = new ButtonBuilder().label("Split the cursor's room").plain().onClick(function () {
            var at = self._mirror.cursor() || self._grid.cells()[0];
            var made = self._grid.subdivide(at, "right");
            say("room " + at + " split in the grid: room " + made + " reflected");
        });
        row.appendChild(split.build(branch.createElement("split", split.tag)).el);

        this.root = root;
        this._reflect();
        say("press a room in the small drawing, or press it and move the cursor with the arrows");
    }

    /** The grid's layout and box, told to the mirror once its holder has put the grid on the page - read then, synchronously. */
    _reflect() {
        var self = this;
        Promise.resolve().then(function () { if (self._alive) self._mirror.reflect(self._grid.layout(), self._grid.box()); });
    }

    /** A mirror varies along no axis. */
    extent(axis, v) {}

    /** The keys its holder hands on: the arrows move the mirror's cursor. */
    key(ev) { return this._mirror.key(ev); }

    dispose() {
        this._alive = false;
        try { this._mirror.dispose(); } catch (e) {}
        try { this._grid.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
