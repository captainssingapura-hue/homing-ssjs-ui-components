// =============================================================================
// StewardMonitorSpecimen — the house's steward monitor in action, on this very
// page: where the keys are, at a glance, as one lamp - held by a member, lent
// by it to a control of its own, or away - with the member a walk offers them
// to, and the keys' invariants: the lamp says the first one broken, in the
// danger colour while one is. Press around the page and it changes. Asked, it
// says what the lamp says, and what is broken.
//
//   new StewardMonitorSpecimen(branch, { leaf, say })   leaf: "steward-monitor"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _stewardMonitorSpecimen = Object.freeze({ toString: () => "stewardMonitorSpecimen" });

class StewardMonitorSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_stewardMonitorSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_row);
        root.appendChild(host);
        this._monitor = new StewardMonitor(branch.createBranch("monitor"), { host: host });
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var ask = new ButtonBuilder().label("What does the lamp say?").plain().onClick(function () {
            var broken = self._monitor.broken();
            say("the lamp: " + self._monitor.lamp() + (broken.length ? " - broken: " + broken[0] : " - every invariant holds"));
        });
        row.appendChild(ask.build(branch.createElement("ask", ask.tag)).el);
        root.appendChild(row);
        this.root = root;
        say("this page's keys, as one lamp: press around the page and it changes");
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
