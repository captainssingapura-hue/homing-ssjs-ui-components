// =============================================================================
// ButtonSpecimen — a house button in action: one of the six, as its leaf says -
// plain, primary, secondary, danger, warning, success - built through the
// button's builder, labelled with what such a button does, and beside it a
// plain button that switches it off and on. A press is said, with how many;
// switched off it is inert and says so, and a press does nothing. Its axes:
// every button's size, and a coloured one's colour - how much of its word's
// meaning it wears, −1 the meaning turned the other way.
//
//   new ButtonSpecimen(branch, { leaf, say })   leaf: "danger-button", …; say(words): what it did
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _buttonSpecimen = Object.freeze({ toString: () => "buttonSpecimen" });

var _BUTTON_KINDS = Object.freeze({
    "plain-button":     Object.freeze({ word: "plain",     label: "Open the report" }),
    "primary-button":   Object.freeze({ word: "primary",   label: "Save" }),
    "secondary-button": Object.freeze({ word: "secondary", label: "Preview" }),
    "danger-button":    Object.freeze({ word: "danger",    label: "Delete the project" }),
    "warning-button":   Object.freeze({ word: "warning",   label: "Overwrite the draft" }),
    "success-button":   Object.freeze({ word: "success",   label: "Finish" })
});

class ButtonSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this, kind = _BUTTON_KINDS[p.leaf];
        if (!kind) throw new Error("[ButtonSpecimen] no button for the leaf '" + p.leaf + "'; one of " + Object.keys(_BUTTON_KINDS).join(", "));
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_buttonSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        root.appendChild(row);

        var presses = 0, on = true;
        var b = new ButtonBuilder().label(kind.label).colour(kind.word).onClick(function () {
            presses++;
            say("\"" + kind.label + "\" pressed - " + presses + (presses === 1 ? " time" : " times"));
        });
        this._button = b.build(branch.createElement("button", b.tag));
        row.appendChild(this._button.el);
        var t = new ButtonBuilder().label("Switch it off").plain().onClick(function () {
            on = !on;
            self._button.setOn(on);
            self._toggle.label(on ? "Switch it off" : "Switch it on");
            say(on ? "on again: a press acts" : "off: inert, and it says so - a press does nothing");
        });
        this._toggle = t.build(branch.createElement("toggle", t.tag));
        row.appendChild(this._toggle.el);

        this.root = root;
        say("a " + kind.word + " button: press it, or switch it off and press it");
    }

    /** Its size, and a coloured one's colour: how much of its word's meaning it wears. */
    extent(axis, v) {
        if (axis === "colour") this._button.extent(v);
        else if (axis === "size") this._button.size(v);
    }

    /** A button takes its keys natively: Enter and Space press the one that has the focus. */
    key(ev) { return false; }

    dispose() { try { this.branch.dissolve(); } catch (e) {} }
}
