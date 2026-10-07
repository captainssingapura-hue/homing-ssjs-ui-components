// =============================================================================
// PaneThumbsSpecimen — the house's pane thumbnails in action: a picture of
// where the panes are, to pick one by pointing at the small one in the same
// place as the big one, each saying how many tabs it holds. It measures what
// is there, so a region split in the dock is in the picture at once; a pane
// that cannot be chosen is shown, not hidden - here, the right one - since
// where it is is part of the picture. Each pick is said. The panes are a room
// of its own, two regions of a dock.
//
//   new PaneThumbsSpecimen(branch, { leaf, say })   leaf: "pane-thumbs"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _paneThumbsSpecimen = Object.freeze({ toString: () => "paneThumbsSpecimen" });

class PaneThumbsSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_paneThumbsSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        root.appendChild(row);
        var room = branch.createElement("room", "div");
        css.addClass(room, sp_host);
        root.appendChild(room);

        this._docks = new SpecimenDocks(branch.createBranch("docks"), { host: room });
        this._docks.source.addTo(this._docks.dock("left"), "note", "quiet");
        this._docks.source.addTo(this._docks.dock("left"), "memo", "quiet");
        this._docks.source.addTo(this._docks.dock("right"), "note", "quiet");
        var regionOf = function (pane) { var r = self._docks.grid.regionOf(pane); return r ? r.id : pane.slotId; };

        this._thumbs = new PaneThumbs(branch.createBranch("thumbs"), {
            host: row, width: "140px",
            panes: function () { return self._docks.docks(); },
            labelOf: regionOf,
            enabledOf: function (pane) { return regionOf(pane) !== "right"; },
            onPick: function (pane) { say("the " + regionOf(pane) + " region picked, by pointing at its picture - " + pane.count() + (pane.count() === 1 ? " tab" : " tabs") + " in it"); }
        });
        var split = new ButtonBuilder().label("Split the left region").plain().onClick(function () {
            self._docks.grid.part("left", "bottom");
            self._thumbs.refresh();
            say("the left region split: the picture takes the new one in");
        });
        row.appendChild(split.build(branch.createElement("split", split.tag)).el);

        this.root = root;
        say("point at a region in the small picture; the right one is shown and cannot be chosen");
    }

    /** The picture varies along no axis. */
    extent(axis, v) {}

    /** Its thumbnails are native buttons: they take their keys natively. */
    key(ev) { return false; }

    dispose() {
        try { this._thumbs.dispose(); } catch (e) {}
        try { this._docks.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
