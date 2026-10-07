// =============================================================================
// AddTabSpecimen — the house's add-tab control in action: put a new tab
// somewhere, saying where - a picture of the panes, pointed at - what - a list
// of the kinds - and how it arrives - quietly, in front, or in front with the
// keys - then one button. It asks whether a pane will take another, and shows
// the answer; it never decides for you. Each tab added is said, with where it
// landed. The panes are a room of its own, two regions of a dock.
//
//   new AddTabSpecimen(branch, { leaf, say })   leaf: "add-tab"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _addTabSpecimen = Object.freeze({ toString: () => "addTabSpecimen" });

class AddTabSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_addTabSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var control = branch.createElement("control", "div");
        css.addClass(control, sp_row);
        root.appendChild(control);
        var room = branch.createElement("room", "div");
        css.addClass(room, sp_host);
        root.appendChild(room);

        this._docks = new SpecimenDocks(branch.createBranch("docks"), { host: room });
        this._docks.source.addTo(this._docks.dock("left"), "note", "quiet");
        this._add = new AddTab(branch.createBranch("add"), {
            host: control, source: this._docks.source, modes: true,
            panes: function () { return self._docks.docks(); },
            onAdded: function (pane, tab, index) {
                var r = self._docks.grid.regionOf(pane);
                say((tab.title ? tab.title() : "a tab") + " added to the " + (r ? r.id : "pane") + " region, at " + index);
            }
        });

        this.root = root;
        say("point at a pane in the small picture, pick what and how, and add it");
    }

    /** The control varies along no axis. */
    extent(axis, v) {}

    /** It is the native world: its buttons and its lists take their keys natively. */
    key(ev) { return false; }

    dispose() {
        try { this._add.dispose(); } catch (e) {}
        try { this._docks.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
