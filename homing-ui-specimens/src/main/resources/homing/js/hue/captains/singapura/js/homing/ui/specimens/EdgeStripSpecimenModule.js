// =============================================================================
// EdgeStripSpecimen — the house's edge strip in action: a box whose tools wait
// out of the way at its foot. The hand on the thin lip at the foot shows the
// strip over the bottom of the box; the hand off both hides it again, a little
// after. A button below holds it shown, and lets it go. What is pressed on
// the strip is said.
//
//   new EdgeStripSpecimen(branch, { leaf, say })   leaf: "edge-strip"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _edgeStripSpecimen = Object.freeze({ toString: () => "edgeStripSpecimen" });

class EdgeStripSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_edgeStripSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        var text = branch.createElement("text", "p");
        css.addClass(text, sp_text);
        text.textContent = "Bring the pointer to the foot of this box: its tools lie there, out of the way until asked for.";
        host.appendChild(text);
        root.appendChild(host);

        this._strip = new EdgeStripBuilder().label("The box's tools").host(host).build(branch.createBranch("strip"));
        ["Copy", "Share", "Archive"].forEach(function (name) {
            var b = new ButtonBuilder().label(name).plain().size(-1).onClick(function () { say(name + " pressed, on the strip"); });
            self._strip.root.appendChild(b.build(branch.createElement("tool-" + name.toLowerCase(), b.tag)).el);
        });

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var held = false;
        var hold = new ButtonBuilder().label("Hold it shown").plain().onClick(function () {
            held = !held;
            self._strip.hold(held);
            self._hold.label(held ? "Let it go" : "Hold it shown");
            say(held ? "held: shown until let go - something on it a person must know" : "let go: it goes once the hand and the keys have");
        });
        this._hold = hold.build(branch.createElement("hold", hold.tag));
        row.appendChild(this._hold.el);
        root.appendChild(row);

        this.root = root;
        say("bring the pointer to the box's foot, or hold the strip shown");
    }

    /** The edge strip's leaf declares no axis. */
    extent(axis, v) {}

    /** It takes no keys of its own; what is on it takes theirs natively. */
    key(ev) { return false; }

    dispose() { this._strip.dispose(); try { this.branch.dissolve(); } catch (e) {} }
}
