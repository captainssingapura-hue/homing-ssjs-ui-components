// =============================================================================
// StewardMonitor — where the focus is, on view, as one lamp: the steward's
// MARKER (RFC 0066 E3, keyboard §17.5) — held by a member, the keys its own;
// lent by it to a native control of its own; or away, the browser's focus
// outside every member and nothing routed — with the member a walk offers
// the keys to, and the steward's invariants: the lamp says the first one
// broken, and turns the danger colour while one is. Redrawn on every event of
// the steward and every move of the native focus. Tooling for a page: it
// takes no keys. A branch component: the caller makes a sub-branch for it
// and hands it in.
//
//   new StewardMonitor(branch, { host })
//     .root           the lamp, appended to the host
//     .lamp()         its text
//     .broken()       the invariants broken at the last redraw, one sentence each
//     .refresh()      redrawn by call; it redraws itself on every change
//     .dispose()
// =============================================================================

const _stewardMonitorOwner = Object.freeze({ toString: () => "stewardMonitor" });

class StewardMonitor {
    constructor(branch, opts) {
        if (!branch) throw new Error("[StewardMonitor] a branch of its own is required");
        if (!opts || !opts.host) throw new Error("[StewardMonitor] opts.host is required");
        var self = this;
        branch.activate(_stewardMonitorOwner);
        this.branch = branch;
        var lamp = branch.createElement("lamp", "div");
        css.addClass(lamp, sm_lamp);
        lamp.setAttribute("role", "status");
        lamp.setAttribute("aria-label", "Keyboard steward");
        opts.host.appendChild(lamp);
        this.root = lamp;
        this._offSteward = KeyboardStewardInstance.on(function () { self.refresh(); });
        this._onFocus = function () { self.refresh(); };
        document.addEventListener("focusin", this._onFocus, false);
        document.addEventListener("focusout", this._onFocus, false);
        this.refresh();
    }

    /** The lamp by the marker. The invariants are read first — before the reading of the marker settles anything — so a missed event shows. */
    refresh() {
        var s = KeyboardStewardInstance, broken = s.check(), at = s.marker(), c = s.candidate(), f = KeyboardSteward.focused();
        var say = !at ? (f ? "away on " + StewardMonitor.describe(f) + " — no one holds the keys" : "no one holds the keys")
            : at.state === "held" ? "held by " + StewardMonitor.who(at.id)
            : at.state === "lent" ? "lent by " + StewardMonitor.who(at.id) + " to " + StewardMonitor.describe(at.el)
            : "away on " + StewardMonitor.describe(f) + " — " + StewardMonitor.who(at.id) + " keeps the mark, nothing is routed";
        if (c) say += " · offered to " + StewardMonitor.who(c);
        if (broken.length) say += " · ✗ " + broken[0];
        this.root.textContent = say;
        this._broken = broken;
        css.toggleClass(this.root, sm_lamp_dormant, !!f && (!at || at.state === "away"));
        css.toggleClass(this.root, sm_lamp_broken, broken.length > 0);
        return this;
    }

    /** A member for the eye: its name in the focus tree, else its id. */
    static who(id) { var m = focusParty.find(id); return "“" + (m ? m.name : id) + "”"; }

    broken() { return (this._broken || []).slice(); }

    /** An element for the eye: its tag, its aria-label or id when it has one. */
    static describe(el) {
        var tag = String(el && el.tagName || "?").toLowerCase();
        var name = el && el.getAttribute ? (el.getAttribute("aria-label") || el.id) : "";
        return name ? tag + " “" + name + "”" : tag;
    }

    lamp() { return this.root.textContent; }

    dispose() {
        this._offSteward();
        document.removeEventListener("focusin", this._onFocus, false);
        document.removeEventListener("focusout", this._onFocus, false);
        if (this.root.parentNode) this.root.parentNode.removeChild(this.root);
        this.branch.dissolve();
    }
}
