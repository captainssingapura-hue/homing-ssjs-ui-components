// =============================================================================
// ToggleWidget — on or off. new ToggleWidget(branch, params) → root, dispose
//   params: { name, label, summary?, default?, on?, off? }
// The store holds "true" or "false"; the switch says which with aria-checked,
// which is what the knob's design word moves on. It follows the store, as its
// field does.
// =============================================================================

const _toggleOwner = Object.freeze({ toString: () => "toggleWidget" });

class ToggleWidget {
    constructor(branch, params) {
        branch.activate(_toggleOwner);
        var self = this;
        this._params = params || {};
        this._field = new PreferenceField(branch.createBranch("field"), Object.assign({}, params, { kicker: "toggle" }));

        var sw = branch.createElement("switch", "button");
        sw.type = "button";
        css.addClass(sw, pv_switch);
        sw.setAttribute("role", "switch");
        var track = branch.createElement("track", "span");
        css.addClass(track, pv_switch_track);
        var knob = branch.createElement("knob", "span");
        css.addClass(knob, pv_switch_knob);
        track.appendChild(knob);
        this._word = branch.createElement("word", "span");
        sw.appendChild(track);
        sw.appendChild(this._word);
        sw.addEventListener("click", function () { self._field.set(self._field.value() === "true" ? "false" : "true"); });
        this._field.hold(sw);
        this._switch = sw;
        this.root = this._field.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._draw(self._field.value()); });
        this._draw(this._field.value());
    }

    _draw(v) {
        var on = v === "true";
        this._switch.setAttribute("aria-checked", on ? "true" : "false");
        this._word.textContent = on ? (this._params.on || "On") : (this._params.off || "Off");
    }

    dispose() { this._stop(); this._field.dispose(); }
}
