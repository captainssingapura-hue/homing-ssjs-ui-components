// =============================================================================
// ControlPanel — the controls for a set of options, each made as its means
// calls for: a slider for a degree, all of them in one group that holds the
// keys, resting at the option's rest; a toggle for a switch, saying whether it
// is on; a button for an action, and for a question. It drives nothing: it
// says what was set or asked, and shows what it is told is set - the panel's
// own callbacks not called, so what it is told is never said back.
//
//   new ControlPanel(branch, { options, onSet, onInvoke, nothing? })
//     options                 option names of the catalogue: a degree for each axis, then a
//                             type's options (Controls.forLeaf); shown sliders, then toggles,
//                             then buttons, each in the order given
//     onSet(option, value, settled)   a degree moved - settled when let go, or by a key -
//                                     or a switch switched (always settled)
//     onInvoke(option)        an action's or a question's button pressed
//     nothing                 what it says with no options: "Nothing to control." unless said
//   panel.root
//   panel.set(option, value)  a degree's number or a switch's truth, shown; true when it has that control
//   panel.key(ev)             a keydown from whoever holds the keys for it: to the sliders; true when taken
//   panel.dispose()
// =============================================================================

const _controlPanelOwner = Object.freeze({ toString: () => "controlPanel" });

class ControlPanel {
    constructor(branch, params) {
        if (!branch) throw new Error("[ControlPanel] a branch of its own is required");
        var p = params || {}, self = this;
        branch.activate(_controlPanelOwner);
        this.branch = branch;
        this._onSet = typeof p.onSet === "function" ? p.onSet : function () {};
        this._onInvoke = typeof p.onInvoke === "function" ? p.onInvoke : function () {};
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

    /** A slider for each degree, in one group. */
    _degrees(names) {
        if (!names.length) return;
        var self = this;
        this._group = new SliderGroupBuilder().title("Varies by degree").build(this.branch.createBranch("degrees"));
        names.forEach(function (n) {
            var o = Controls.option(n);
            self._sliders[n] = self._group.add(n, new SliderBuilder().label(o.label).icon(n === "colour" ? "extent" : n)
                .range(-1, 1, 0.05).detent(o.rest).value(o.rest)
                .format(function (x) { return x.toFixed(2) + (x === o.rest ? "  at rest" : ""); })
                .onInput(function (x) { self._onSet(n, x, false); })
                .onChange(function (x) { self._onSet(n, x, true); }));
        });
        this.root.appendChild(this._group.root);
    }

    /** A toggle for each switch, saying whether it is on. */
    _toggles(names) {
        if (!names.length) return;
        var self = this, row = this._row("switches");
        names.forEach(function (n) {
            var o = Controls.option(n), s = { on: !!o.rest, button: null };
            var b = new ButtonBuilder().label(ControlPanel._said(o, s.on)).plain().onClick(function () {
                s.on = !s.on;
                s.button.label(ControlPanel._said(o, s.on));
                self._onSet(n, s.on, true);
            });
            s.button = b.build(self.branch.createElement("switch-" + n, b.tag));
            row.appendChild(s.button.el);
            self._switches[n] = s;
        });
    }

    /** A button for each action and question. */
    _buttons(names) {
        if (!names.length) return;
        var self = this, row = this._row("acts");
        names.forEach(function (n) {
            var b = new ButtonBuilder().label(Controls.option(n).label).plain().onClick(function () { self._onInvoke(n); });
            row.appendChild(b.build(self.branch.createElement("act-" + n, b.tag)).el);
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
            s.button.label(ControlPanel._said(o, s.on));
            return true;
        }
        return false;
    }

    key(ev) { return this._group ? this._group.key(ev) : false; }

    dispose() {
        if (this._group) { try { this._group.dispose(); } catch (e) {} this._group = null; }
        try { this.branch.dissolve(); } catch (e) {}
    }
}
