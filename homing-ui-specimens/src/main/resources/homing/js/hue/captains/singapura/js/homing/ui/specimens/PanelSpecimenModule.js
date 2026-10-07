// =============================================================================
// PanelSpecimen — the house's panel in action: one concern of a view, named,
// filling the box it is put in, with what acts on it in its head - here, a
// button that marks it the current one, said in colour alone, and one that
// lifts it off its plane, sinks it, and sets it flat again. A panel takes no
// keys and measures nothing; what it does is said.
//
//   new PanelSpecimen(branch, { leaf, say })   leaf: "panel"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _panelSpecimen = Object.freeze({ toString: () => "panelSpecimen" });

var _ELEVATIONS = Object.freeze([null, "elevated", "sunken"]);

class PanelSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_panelSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        root.appendChild(host);

        this._panel = new PanelBuilder().title("Orders to pack").host(host).build(branch.createBranch("panel"));
        var text = branch.createElement("text", "p");
        css.addClass(text, sp_text);
        text.textContent = "Twelve orders wait to be packed; three of them leave today.";
        this._panel.body.appendChild(text);

        var current = false;
        var mark = new ButtonBuilder().label("Make it current").plain().size(-1).onClick(function () {
            current = !current;
            self._panel.highlight(current);
            self._mark.label(current ? "Not current" : "Make it current");
            say(current ? "the current one: said in colour, nothing else moves" : "no longer the current one");
        });
        this._mark = mark.build(branch.createElement("mark", mark.tag));
        this._panel.controls.appendChild(this._mark.el);
        var at = 0;
        var lift = new ButtonBuilder().label("Lift it").plain().size(-1).onClick(function () {
            at = (at + 1) % _ELEVATIONS.length;
            self._panel.elevation(_ELEVATIONS[at]);
            self._lift.label(at === 0 ? "Lift it" : at === 1 ? "Sink it" : "Set it flat");
            say(at === 0 ? "flat: on its own plane" : at === 1 ? "elevated: off its plane, as the design has it" : "sunken: below its plane");
        });
        this._lift = lift.build(branch.createElement("lift", lift.tag));
        this._panel.controls.appendChild(this._lift.el);

        this.root = root;
        say("a panel fills what holds it: mark it current, or lift and sink it");
    }

    /** The panel's leaf declares no axis. */
    extent(axis, v) {}

    /** A panel takes no keys; its buttons take theirs natively. */
    key(ev) { return false; }

    dispose() { this._panel.dispose(); try { this.branch.dissolve(); } catch (e) {} }
}
