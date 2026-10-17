// =============================================================================
// ControlPanel — the controls for a set of options, each made as its means
// calls for: a slider for a degree, all of them in one group that holds the
// keys, resting at the option's rest; a toggle for a switch, saying whether it
// is on; a button for an action, and for a question. It drives nothing: it
// says what was set or asked, and shows what it is told is set - what it is
// told never said back. A widget: its holder shows it in a slot, and operates
// it through the instance.
//
//   new ControlPanel(branch, { options, nothing? })
//     options                 option names of the catalogue: a degree for each axis, then a
//                             type's options (Controls.forLeaf); shown sliders, then toggles,
//                             then buttons, each in the order given
//     nothing                 what it says with no options: "Nothing to control." unless said
//   panel.root
//   panel.onSet(fn)           fn(option, value, settled) when a degree moved - settled when let
//                             go, or by a key - or a switch switched (always settled)
//   panel.onInvoke(fn)        fn(option) when an action's or a question's button is pressed
//   panel.set(option, value)  a degree's number or a switch's truth, shown; true when it has that control
//   panel.key(ev)             a keydown from whoever holds the keys for it: to the sliders; true when taken
//   panel.dispose()
// =============================================================================

const _controlPanelOwner = Object.freeze({ toString: () => "controlPanel" });

/** A switch's button pressed: the switch turned over, its words with it, and the panel's holder told. */
class ControlPanelSwitch {
    constructor(panel, name, option, sw) { this._panel = panel; this._name = name; this._option = option; this._sw = sw; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle() {
        this._sw.on = !this._sw.on;
        this._sw.button.label = ControlPanel._said(this._option, this._sw.on);
        this._panel._set(this._name, this._sw.on, true);
    }
}

/** An action's or a question's button pressed: the panel's holder told which. */
class ControlPanelAct {
    constructor(panel, name) { this._panel = panel; this._name = name; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle() { this._panel._invoke(this._name); }
}

class ControlPanel {
    constructor(branch, params) {
        if (!branch) throw new Error("[ControlPanel] a branch of its own is required");
        var p = params || {};
        branch.activate(_controlPanelOwner);
        this.branch = branch;
        this._setters = [];
        this._invokers = [];
        this._sliders = {};
        this._switches = {};
        this._group = null;
        var names = (p.options || []).slice();
        names.forEach(function (n) { if (!Controls.option(n)) throw new Error("[ControlPanel] no option '" + n + "' in the catalogue"); });
        var root = branch.createElement("panel", "div");
        css.addClass(root, cp_panel);
        this.root = root;
        if (!names.length) {
            var note = branch.createElement("nothing", "p");
            css.addClass(note, cp_note);
            note.textContent = p.nothing || "Nothing to control.";
            root.appendChild(note);
            return;
        }
        var by = function (means) { return names.filter(function (n) { return means.indexOf(Controls.option(n).means) >= 0; }); };
        this._degrees(by(["extent"]));
        this._toggles(by(["switch"]));
        this._buttons(by(["action", "question"]));
    }

    /** fn(option, value, settled), each time a degree moves or a switch is switched. */
    onSet(fn) { this._setters.push(fn); }

    /** fn(option), each time an action's or a question's button is pressed. */
    onInvoke(fn) { this._invokers.push(fn); }

    _set(name, value, settled) { this._setters.forEach(function (fn) { fn(name, value, settled); }); }

    _invoke(name) { this._invokers.forEach(function (fn) { fn(name); }); }

    /** A slider for each degree, in one group. */
    _degrees(names) {
        if (!names.length) return;
        var self = this;
        this._group = new SliderGroup(this.branch.createBranch("degrees"), { title: "Varies by degree" }, ROLE.ADJUST);   // its group, cast as what adjusts
        names.forEach(function (n) {
            var o = Controls.option(n);
            self._sliders[n] = self._group.add(n, { label: o.label, icon: ({ size: ICON.SIZE_ICON, aspect: ICON.ASPECT_ICON, extent: ICON.EXTENT_ICON, colour: ICON.EXTENT_ICON })[n] || null,
                min: -1, max: 1, step: 0.05, detents: [o.rest], value: o.rest,
                format: function (x) { return x.toFixed(2) + (x === o.rest ? "  at rest" : ""); },
                onInput: function (x) { self._set(n, x, false); },
                onChange: function (x) { self._set(n, x, true); } });
        });
        this.root.appendChild(this._group.root);
    }

    /** A toggle for each switch, saying whether it is on. */
    _toggles(names) {
        if (!names.length) return;
        var self = this, row = this._row("switches");
        names.forEach(function (n) {
            var o = Controls.option(n), s = { on: !!o.rest, button: null };
            s.button = new PlainButton(PlainButtonFactory.create(self.branch, "switch-" + n), { label: ControlPanel._said(o, s.on) }, ROLE.TOGGLE);
            s.button.setOnClick(new ControlPanelSwitch(self, n, o, s));
            row.appendChild(s.button.el);
            self._switches[n] = s;
        });
    }

    /** A button for each action and question. */
    _buttons(names) {
        if (!names.length) return;
        var self = this, row = this._row("acts");
        names.forEach(function (n) {
            var act = new PlainButton(PlainButtonFactory.create(self.branch, "act-" + n), { label: Controls.option(n).label }, ROLE.ACTION);
            act.setOnClick(new ControlPanelAct(self, n));
            row.appendChild(act.el);
        });
    }

    _row(name) {
        var row = this.branch.createElement(name, "div");
        css.addClass(row, cp_row);
        this.root.appendChild(row);
        return row;
    }

    static _said(o, on) { return o.label + ": " + (on ? "on" : "off"); }

    set(name, value) {
        var o = Controls.option(name);
        if (!o) return false;
        if (o.means === "extent" && this._sliders[name]) { this._sliders[name].value(Number(value)); return true; }
        if (o.means === "switch" && this._switches[name]) {
            var s = this._switches[name];
            s.on = !!value;
            s.button.label = ControlPanel._said(o, s.on);
            return true;
        }
        return false;
    }

    key(ev) { return this._group ? this._group.key(ev) : false; }

    dispose() {
        if (this._group) { try { this._group.dispose(); } catch (e) {} this._group = null; }
        this._setters = [];
        this._invokers = [];
    }
}
