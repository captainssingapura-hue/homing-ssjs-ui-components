// =============================================================================
// ToggleWidget — on or off. new ToggleWidget(branch, params) → root, dispose
//   params: { name, label, summary?, default?, on?, off? }
// The store holds "true" or "false". Its switch is the house's, cast as the
// widget's toggle, its words the state it says: turned by a press, the store is
// set; it follows the store, as its field does.
// =============================================================================

const _toggleOwner = Object.freeze({ toString: () => "toggleWidget" });

/** The switch turned by a press: the store set. */
class _ToggleTurned {
    constructor(widget) { this._widget = widget; }
    canHandle(type) { return type === EVENT.COMMIT; }
    handle(e) { this._widget._field.set(this._widget._switch.checked ? "true" : "false"); }
}

class ToggleWidget {
    constructor(branch, params) {
        branch.activate(_toggleOwner);
        var self = this;
        this._params = params || {};
        this._field = new PreferenceField(branch.createBranch("field"), Object.assign({}, params, { kicker: "toggle" }));

        this._switch = new Switch(branch.createBranch("switch"), {}, ROLE.TOGGLE);   // the house's switch, as the widget's toggle
        this._switch.setOnCommit(new _ToggleTurned(this));
        this._field.hold(this._switch.root);
        this.root = this._field.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._draw(self._field.value()); });
        this._draw(this._field.value());
    }

    _draw(v) {
        var on = v === "true";
        this._switch.checked = on;
        this._switch.text = on ? (this._params.on || "On") : (this._params.off || "Off");
    }

    dispose() { this._stop(); this._field.dispose(); }
}
