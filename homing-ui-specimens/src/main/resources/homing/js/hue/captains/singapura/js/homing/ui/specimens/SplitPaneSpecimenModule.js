// =============================================================================
// SplitPaneSpecimen — the house's split pane in action: a list beside a detail
// over its notes, a room shared by ratio between regions split and split
// again. The user drags a divider to give one region more room, each kept at
// its least; the new shares are said once, when the hand lets go. The buttons
// re-share the outer split by call.
//
//   new SplitPaneSpecimen(branch, { leaf, say })   leaf: "split-pane"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _splitPaneSpecimen = Object.freeze({ toString: () => "splitPaneSpecimen" });

class SplitPaneSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_splitPaneSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        root.appendChild(host);
        var shares = function (rs) { return rs.map(function (r) { return Math.round(r * 100) + "%"; }).join(" : "); };
        this._split = new SplitPane(branch.createBranch("split"), {
            host: host,
            layout: { kind: "split", orientation: "horizontal", children: [
                { pane: { kind: "leaf", slotId: "list" }, ratio: 1 },
                { pane: { kind: "split", orientation: "vertical", children: [
                    { pane: { kind: "leaf", slotId: "detail" } },
                    { pane: { kind: "leaf", slotId: "notes" } }] }, ratio: 2 }] },
            onEvent: function (ev) {
                if (ev.kind === "RatioChanged") say((ev.path.length ? "the inner split" : "the outer split") + " re-shared: " + shares(ev.ratios));
            }
        });
        var words = { list: "The list", detail: "The detail", notes: "Its notes" };
        this._split.slots().forEach(function (id) {
            var t = branch.createElement("slot-" + id, "p");
            css.addClass(t, sp_text);
            t.textContent = words[id];
            self._split.slot(id).appendChild(t);
        });

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var even = new ButtonBuilder().label("Even the outer split").plain().onClick(function () { self._split.setRatios("", [1, 1]); });
        row.appendChild(even.build(branch.createElement("even", even.tag)).el);
        var back = new ButtonBuilder().label("One to two again").plain().onClick(function () { self._split.setRatios("", [1, 2]); });
        row.appendChild(back.build(branch.createElement("back", back.tag)).el);
        root.appendChild(row);

        this.root = root;
        say("drag a divider to give one region more room; the shares are said when the hand lets go");
    }

    /** A split varies along no axis. */
    extent(axis, v) {}

    /** It takes no keys. */
    key(ev) { return false; }

    dispose() {
        try { this._split.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
