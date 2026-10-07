// =============================================================================
// SliderSpecimen — the house's slider in action: a value on a scale the user
// can see - its name, a tick and a figure at each quarter, a detent it rests
// at, the value read out as it moves. Dragged, pressed anywhere on the rail,
// or moved by the keys handed on - the arrows by a step, with Shift by ten,
// Home and End, PageUp and PageDown - every move is said, and every value set.
// Switched off, it is inert and says so.
//
//   new SliderSpecimen(branch, { leaf, say })   leaf: "slider"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _sliderSpecimen = Object.freeze({ toString: () => "sliderSpecimen" });

class SliderSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_sliderSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);

        this._slider = new SliderBuilder()
            .label("Volume")
            .range(0, 100, 1).value(40).detent(50)
            .ticks([{ at: 0, label: "0" }, { at: 25, label: "25" }, { at: 50, label: "50" }, { at: 75, label: "75" }, { at: 100, label: "100" }])
            .format(function (v) { return v + "%"; })
            .onInput(function (v) { say("moving: " + v + "%"); })
            .onChange(function (v) { say("set to " + v + "%" + (v === 50 ? " - rested at the detent" : "")); })
            .build(branch.createBranch("slider"));
        root.appendChild(this._slider.root);

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var on = true;
        var t = new ButtonBuilder().label("Switch it off").plain().onClick(function () {
            on = !on;
            self._slider.setOn(on);
            self._toggle.label(on ? "Switch it off" : "Switch it on");
            say(on ? "on again" : "off: inert, and it says so");
        });
        this._toggle = t.build(branch.createElement("toggle", t.tag));
        row.appendChild(this._toggle.el);
        root.appendChild(row);

        this.root = root;
        say("drag the knob, press the rail anywhere, or press it and use the arrows, Home, End, PageUp and PageDown");
    }

    /** The slider's leaf declares no axis. */
    extent(axis, v) {}

    /** The keys its holder hands on: to the slider. */
    key(ev) { return this._slider.key(ev); }

    dispose() { this._slider.dispose(); try { this.branch.dissolve(); } catch (e) {} }
}
