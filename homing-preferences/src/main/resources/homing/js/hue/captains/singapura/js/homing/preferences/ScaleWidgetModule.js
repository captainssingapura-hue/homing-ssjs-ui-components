// =============================================================================
// ScaleWidget — a number in a range. new ScaleWidget(branch, params) → root, dispose
//   params: { name, label, summary?, default?, min, max, step?, unit? }
// The store holds the number as a string; the readout shows it with its unit.
// It follows the store, as its field does. Its range is the house's, cast as
// what adjusts: the readout follows it moving, the field is set when let go.
// =============================================================================

/** The range moving: the readout follows. */
class _ScaleMoving {
    constructor(widget) { this._widget = widget; }
    canHandle(type) { return type === EVENT.INPUT; }
    handle(e) { this._widget._readout.text = this._widget._range.value + this._widget._unit; }
}

/** The range let go: the field set. */
class _ScaleLetGo {
    constructor(widget) { this._widget = widget; }
    canHandle(type) { return type === EVENT.COMMIT; }
    handle(e) { this._widget._field.set(String(this._widget._range.value)); }
}

const _scaleOwner = Object.freeze({ toString: () => "scaleWidget" });

class ScaleWidget {
    constructor(branch, params) {
        branch.activate(_scaleOwner);
        var self = this;
        this._unit = params.unit || "";
        this._field = new PreferenceField(branch.createBranch("field"), Object.assign({}, params, { kicker: "scale" }));

        this._readout = new Readout(ReadoutFactory.create(branch, "readout"), {}, ROLE.VALUE);
        css.addClass(this._readout.el, pv_readout);
        var range = new Range(branch.elementMaker("range"), { min: params.min, max: params.max, step: params.step || 1 }, ROLE.ADJUST);   // what adjusts
        css.addClass(range.el, pv_range);
        range.el.setAttribute("aria-label", params.label || params.name);
        range.setOnInput(new _ScaleMoving(this));
        range.setOnCommit(new _ScaleLetGo(this));
        this._field.hold(range.el);
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
