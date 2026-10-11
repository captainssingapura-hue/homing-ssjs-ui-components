// =============================================================================
// ToggleWidget — on or off. new ToggleWidget(branch, params) → root, dispose
//   params: { name, label, summary?, default?, on?, off? }
// The store holds "true" or "false"; the switch says which with aria-checked.
// It follows the store, as its field does.
//
// The switch wears the house's look, drawn here: a switch cast as the widget's
// toggle, which is a slider's groove that is a control - the groove's colours,
// lines and ease, the switch's ring and the rest - with a slider's knob in it;
// their sizes are the switch's own, and the knob slides by its translate. Its
// word sits beside it, the switch's own room between them.
// =============================================================================

const _toggleOwner = Object.freeze({ toString: () => "toggleWidget" });
/** The switch's leaves, drawn here: a switch, and the groove and knob it is made of. */
const _SWITCH = Object.freeze({ LEAF: "switch" });
const _GROOVE = Object.freeze({ LEAF: "groove" });
const _KNOB = Object.freeze({ LEAF: "knob" });
/** What of the groove's and the knob's looks the switch wears: their colours, their lines, how they ease - never their sizes, which are the switch's. */
const _OF_THE_GROOVE = Object.freeze(["color-background", "color-border", "shape-corner", "shape-border", "motion-ease"]);
/** What of the switch's look its row wears: the room between the switch and its word. */
const _OF_THE_ROW = Object.freeze(["size-gap"]);

class ToggleWidget {
    constructor(branch, params) {
        branch.activate(_toggleOwner);
        var self = this;
        this._params = params || {};
        this._field = new PreferenceField(branch.createBranch("field"), Object.assign({}, params, { kicker: "toggle" }));

        var look = new Look(_SWITCH, ROLE.TOGGLE);
        var row = branch.createElement("switch", "span");   // the switch and its word
        css.addClass(row, pv_switch);
        look.wear(row, { only: _OF_THE_ROW });
        var sw = branch.createElement("groove", "button");   // the switch: a groove that is a control
        sw.type = "button";
        css.addClass(sw, pv_switch_track);
        look.wear(sw, { except: _OF_THE_GROOVE.concat(_OF_THE_ROW) });
        new Look(_GROOVE, ROLE.SPAN).wear(sw, { only: _OF_THE_GROOVE });
        sw.setAttribute("role", "switch");
        var knob = branch.createElement("knob", "span");
        css.addClass(knob, pv_switch_knob);
        new Look(_KNOB, ROLE.THUMB).wear(knob, { only: _OF_THE_GROOVE });
        sw.appendChild(knob);
        this._word = branch.createElement("word", "span");
        row.appendChild(sw);
        row.appendChild(this._word);
        sw.addEventListener("click", function () { self._field.set(self._field.value() === "true" ? "false" : "true"); });
        this._field.hold(row);
        this._switch = sw;
        this.root = this._field.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._draw(self._field.value()); });
        this._draw(this._field.value());
    }

    _draw(v) {
        var on = v === "true";
        this._switch.setAttribute("aria-checked", on ? "true" : "false");   // the switch is its groove: the groove turns on with it
        this._word.textContent = on ? (this._params.on || "On") : (this._params.off || "Off");
    }

    dispose() { this._stop(); this._field.dispose(); }
}
