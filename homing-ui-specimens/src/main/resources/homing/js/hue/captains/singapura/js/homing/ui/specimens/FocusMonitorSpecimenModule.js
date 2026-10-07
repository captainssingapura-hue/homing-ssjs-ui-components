// =============================================================================
// FocusMonitorSpecimen — the house's focus monitor in action, on this very
// page: its logical-focus tree as a tree view, a row per node, indented by
// depth, the row of the member that holds the keys lit. Press anywhere on the
// page - the tree, a widget, this one - and the lit row follows. Asked, it
// says who holds the keys now.
//
//   new FocusMonitorSpecimen(branch, { leaf, say })   leaf: "focus-monitor"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _focusMonitorSpecimen = Object.freeze({ toString: () => "focusMonitorSpecimen" });

class FocusMonitorSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_focusMonitorSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        root.appendChild(row);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_scroll);
        root.appendChild(host);
        this._monitor = new FocusMonitor(branch.createBranch("monitor"), { host: host });
        var who = new ButtonBuilder().label("Who holds the keys?").plain().onClick(function () {
            var lit = self._monitor.refresh().holderRow();   // drawn now, so the row read is the row shown
            var parts = lit ? Array.prototype.map.call(lit.children, function (c) { return c.textContent.trim(); }).filter(Boolean) : [];
            say(lit ? "the lit row: " + (parts.length ? parts.join(" · ") : lit.textContent.trim()) : "nobody holds the keys");
        });
        row.appendChild(who.build(branch.createElement("who", who.tag)).el);
        this.root = root;
        say("this page's focus tree: press anywhere on the page and the lit row follows");
    }

    /** A monitor varies along no axis. */
    extent(axis, v) {}

    /** It takes no keys: it reads them. */
    key(ev) { return false; }

    dispose() {
        try { this._monitor.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
