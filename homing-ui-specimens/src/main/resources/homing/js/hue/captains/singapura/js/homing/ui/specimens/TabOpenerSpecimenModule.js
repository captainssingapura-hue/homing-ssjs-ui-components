// =============================================================================
// TabOpenerSpecimen — the house's tab opener in action: what a new tab holds
// until you say what it should hold. It offers the kinds there are; picking one
// TURNS THAT TAB INTO the thing you picked - the chip you made is the chip you
// keep, in the place you made it. Each becoming is said. The tabs are in a room
// of its own, two regions of a dock; a button opens another chooser.
//
//   new TabOpenerSpecimen(branch, { leaf, say })   leaf: "tab-opener"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _tabOpenerSpecimen = Object.freeze({ toString: () => "tabOpenerSpecimen" });

class TabOpenerSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_tabOpenerSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var room = branch.createElement("room", "div");
        css.addClass(room, sp_host);
        root.appendChild(room);

        this._docks = new SpecimenDocks(branch.createBranch("docks"), {
            host: room,
            kinds: [{ id: "opener", label: "Open…", title: "Open", listed: false,
                      make: function (b, q) { return new TabOpener(b, { focus: q.focus, tab: q.tab, source: self._docks.source }); } }],
            onBecame: function (tp, kindId) { say("the chooser's tab became a " + kindId + " - the same chip, in the same place"); }
        });
        this._docks.source.addTo(this._docks.dock("left"), "note", "quiet");
        this._docks.source.addTo(this._docks.dock("left"), "opener", "front");

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var another = new ButtonBuilder().label("Open another chooser").plain().onClick(function () {
            var at = self._docks.source.addTo(self._docks.dock("right"), "opener", "front");
            say(at < 0 ? "no room for another tab" : "a chooser opened in the right region");
        });
        row.appendChild(another.build(branch.createElement("another", another.tag)).el);
        root.appendChild(row);

        this.root = root;
        say("a chooser in the left region: pick what its tab should hold");
    }

    /** The opener varies along no axis. */
    extent(axis, v) {}

    /** Its choices are native buttons: they take their keys natively. */
    key(ev) { return false; }

    dispose() {
        try { this._docks.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
