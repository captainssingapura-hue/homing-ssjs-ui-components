// =============================================================================
// SpecimenDocks — a specimen's room for tabs: a desk of its own, two regions of
// a dock grid on it, and a source of plain tabs - a note and a memo - beside
// any kinds the specimen adds, so the controls that put tabs somewhere have
// somewhere real to put them. Not a house component shown for itself: the
// room the tab controls are shown in.
//
//   new SpecimenDocks(branch, { host, kinds?, onBecame? })
//     host   the positioned flex box the desk and its grid fill
//     kinds  more of the source's kinds, as TabSource takes them
//   docks.desk .grid .source
//   docks.docks()       the regions' docks, in the order they were made
//   docks.dock(id)      "left" or "right"
//   docks.dispose()
// =============================================================================

const _specimenDocks = Object.freeze({ toString: () => "specimenDocks" });
var _specimenDesks = 0;

/** A tab's widget by the pane's law: a member of the branch it is handed, answering activate(), its Escape yielding. */
class _SpecimenNote {
    constructor(branch, params) {
        branch.activate(_specimenDocks);
        this.root = branch.createElement("note", "p");
        css.addClass(this.root, sp_text);
        this.root.textContent = params.text;
        this.focus = params.focus.join(branch.name, this);
        this._off = Keys.claimOn(this.root, this.focus);
    }
    activate() { Keys.claim(this.focus); }
    keyDown(ev) { if (ev.key === "Escape") { Keys.yield(this.focus); return true; } return false; }
    dispose() { if (this._off) this._off(); if (this.focus && this.focus.in) this.focus.leave(); }
}

class SpecimenDocks {
    constructor(branch, opts) {
        var o = opts || {};
        if (!o.host) throw new Error("[SpecimenDocks] a host is required: the box the desk fills");
        branch.activate(_specimenDocks);
        this.branch = branch;
        this.desk = new Desk(branch.createBranch("desk"), { host: o.host, budget: 6, focusName: "specimen-desk-" + (++_specimenDesks) });
        // the docks' focus branches under the desk's own, which is named for this room alone - never at the page's
        // root, where the workspace's docks are named as these would be
        this.grid = new DockGrid(branch.createBranch("docks"), { host: o.host, desk: this.desk, dock: { focus: this.desk.focus },
            layout: { kind: "split", orientation: "horizontal", children: [
                { node: { kind: "cell", id: "left" }, ratio: 1 }, { node: { kind: "cell", id: "right" }, ratio: 1 }] } });
        var kinds = [
            { id: "note", label: "A note", title: "Note", make: function (b, p) { return new _SpecimenNote(b, { focus: p.focus, text: "A note: the budget is due on Friday." }); } },
            { id: "memo", label: "A memo", title: "Memo", make: function (b, p) { return new _SpecimenNote(b, { focus: p.focus, text: "A memo: the spring launch moves a week." }); } }
        ].concat(o.kinds || []);
        this.source = new TabSource(branch.createBranch("source"), { desk: this.desk, kinds: kinds, onBecame: o.onBecame });
    }

    docks() { return this.grid.regions().map(function (r) { return r.dock; }); }

    dock(id) { var r = this.grid.region(id); return r ? r.dock : null; }

    dispose() {
        try { this.grid.dispose(); } catch (e) {}
        try { this.desk.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
