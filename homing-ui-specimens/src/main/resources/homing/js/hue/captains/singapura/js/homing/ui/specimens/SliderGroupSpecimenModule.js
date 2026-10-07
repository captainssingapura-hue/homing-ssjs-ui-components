// =============================================================================
// SliderGroupSpecimen — the house's slider group in action: three faders that
// set related values together, under one heading, holding the keys as one.
// The keys handed on go to the current fader; Tab and Shift+Tab make the next
// or the previous one current, wrapping; a press on a rail makes its fader
// current. Every value set is said, with whose it is.
//
//   new SliderGroupSpecimen(branch, { leaf, say })   leaf: "slider-group"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _sliderGroupSpecimen = Object.freeze({ toString: () => "sliderGroupSpecimen" });

class SliderGroupSpecimen {
    constructor(branch, params) {
        var p = params || {};
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_sliderGroupSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);

        this._group = new SliderGroupBuilder().title("Equaliser").across().build(branch.createBranch("group"));
        var self = this;
        ["Bass", "Middle", "Treble"].forEach(function (name) {
            self._group.add(name.toLowerCase(), new SliderBuilder().label(name).vertical().axis()
                .format(function (v) { return (v > 0 ? "+" : "") + v.toFixed(1); })
                .onChange(function (v) { say(name + " set to " + (v > 0 ? "+" : "") + v.toFixed(1)); }));
        });
        root.appendChild(this._group.root);

        this.root = root;
        say("three faders, holding the keys as one: Tab moves between them, the arrows move the current one");
    }

    /** The group's leaf declares no axis. */
    extent(axis, v) {}

    /** The keys its holder hands on: to the group, which hands them to its current fader. */
    key(ev) { return this._group.key(ev); }

    dispose() { this._group.dispose(); try { this.branch.dissolve(); } catch (e) {} }
}
