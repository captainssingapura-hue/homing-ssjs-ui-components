// =============================================================================
// ScaleWidget — a number in a range. new ScaleWidget(branch, params) → root, dispose
//   params: { name, label, summary?, default?, min, max, step?, unit? }
// The store holds the number as a string; the readout shows it with its unit.
// It follows the store, as its field does.
// =============================================================================

const _scaleOwner = Object.freeze({ toString: () => "scaleWidget" });

class ScaleWidget {
    constructor(branch, params) {
        branch.activate(_scaleOwner);
        var self = this;
        this._unit = params.unit || "";
        this._field = new PreferenceField(branch.createBranch("field"), Object.assign({}, params, { kicker: "scale" }));

        this._readout = new Readout(ReadoutFactory.create(branch, "readout"), {}, ROLE.VALUE);
        css.addClass(this._readout.el, pv_readout);
        var range = branch.createElement("range", "input");
        range.type = "range";
        css.addClass(range, pv_range);
        new Look(Object.freeze({ LEAF: "range" }), ROLE.ADJUST).wear(range);   // a range, as what adjusts: in the accent, ringed as a control
        range.min = params.min; range.max = params.max; range.step = params.step || 1;
        range.setAttribute("aria-label", params.label || params.name);
        range.addEventListener("input", function () { self._readout.text = range.value + self._unit; });
        range.addEventListener("change", function () { self._field.set(String(range.value)); });
        this._field.hold(range);
        this._field.hold(this._readout.el);
        this._range = range;
        this.root = this._field.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._draw(self._field.value()); });
        this._draw(this._field.value());
    }

    _draw(v) {
        var range = this._range;
        if (v !== null && v !== undefined && String(range.value) !== String(v)) range.value = v;
        this._readout.text = (v == null ? range.value : v) + this._unit;
    }

    dispose() { this._stop(); this._field.dispose(); }
}
