// =============================================================================
// FloatingPaneSpecimen — the house's floating pane in action: content in a
// window of its own over the box, which the user moves by its head and sizes
// by its grip, its place and measure clamped to the box it floats in. Moved
// and resized are said once, when the hand lets go. Its cross asks its holder
// to close it - here the holder does, and it can be opened again. The buttons
// move it, size it and ring it by call.
//
//   new FloatingPaneSpecimen(branch, { leaf, say })   leaf: "floating-pane"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _floatingPaneSpecimen = Object.freeze({ toString: () => "floatingPaneSpecimen" });

class FloatingPaneSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_floatingPaneSpecimen);
        this.branch = branch;
        this._say = say;
        this._pane = null;
        this._opened = 0;
        this._ringed = false;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        this._host = branch.createElement("host", "div");
        css.addClass(this._host, sp_host);
        root.appendChild(this._host);

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var b = function (name, label, fn) {
            var x = new ButtonBuilder().label(label).plain().onClick(fn);
            row.appendChild(x.build(branch.createElement(name, x.tag)).el);
        };
        b("corner", "Move it to the far corner", function () {
            if (!self._pane) return say("closed: open it again first");
            self._pane.moveTo(10000, 10000);
            say("asked to go past the corner: clamped to the box");
        });
        b("bigger", "Make it bigger", function () {
            if (!self._pane) return say("closed: open it again first");
            var r = self._pane.bounds();
            self._pane.resizeTo(r.w + 60, r.h + 40);
        });
        b("ring", "Ring it", function () {
            if (!self._pane) return say("closed: open it again first");
            self._ringed = !self._ringed;
            self._pane.setActive(self._ringed);
            say(self._ringed ? "ringed: the active one" : "no ring: not the active one");
        });
        b("again", "Open it again", function () { self._open(); });
        root.appendChild(row);

        this.root = root;
        this._open();
        say("move it by its head, size it by the grip at its corner, close it by its cross");
    }

    _open() {
        if (this._pane) { this._say("open already"); return; }
        var self = this, say = this._say, n = ++this._opened;
        this._pane = new FloatingPane(this.branch.createBranch("pane-" + n), {
            id: "notes", title: "Notes", x: 16, y: 12, w: 240, h: 120, z: 1, closable: true,
            onEvent: function (ev) {
                if (ev.kind === "Moved") say("moved to " + ev.x + ", " + ev.y);
                else if (ev.kind === "Resized") say("resized to " + ev.w + " by " + ev.h);
            },
            onClose: function (pane) {
                say("its cross asked its holder to close it - and the holder did");
                pane.dispose();
                self._pane = null;
            }
        });
        var text = this.branch.createElement("note-" + n, "p");
        css.addClass(text, sp_text);
        text.textContent = "Notes for the meeting: the budget, the hiring plan, the spring launch.";
        this._pane.body.appendChild(text);
        this._host.appendChild(this._pane.root);
        if (n > 1) say("open again, where it began");
    }

    /** A floating pane's leaf declares no axis. */
    extent(axis, v) {}

    /** It takes no keys: its holder's are its own. */
    key(ev) { return false; }

    dispose() {
        if (this._pane) { try { this._pane.dispose(); } catch (e) {} this._pane = null; }
        try { this.branch.dissolve(); } catch (e) {}
    }
}
