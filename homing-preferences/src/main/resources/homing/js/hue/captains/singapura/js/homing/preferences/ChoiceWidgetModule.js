// =============================================================================
// ChoiceWidget — one of a list. new ChoiceWidget(branch, params) → root, dispose
//   params: { name, label, summary?, default?, options: [{ value, label, note? }] }
// The options are a listbox; the one in force is aria-selected; a click or
// Enter/Space on an option writes it through the steward; it follows the
// store, as its field does, so the mark moves wherever the change came from.
// =============================================================================

const _choiceOwner = Object.freeze({ toString: () => "choiceWidget" });

/** An option pressed: the field set to its value. */
class ChoicePicked {
    constructor(field, value) { this._field = field; this._value = value; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle() { this._field.set(this._value); }
}

class ChoiceWidget {
    constructor(branch, params) {
        branch.activate(_choiceOwner);
        var self = this;
        var options = (params && params.options) || [];
        this._rows = [];
        this._field = new PreferenceField(branch.createBranch("field"), Object.assign({}, params, { kicker: "choice" }));

        var list = branch.createElement("options", "div");
        css.addClass(list, pv_options);
        list.setAttribute("role", "listbox");
        list.setAttribute("aria-label", params.label || params.name);
        options.forEach(function (opt, i) {
            // each option the house's choice option, cast as a choice of the setting: its name and its note its own; placed by the widget
            var option = new ChoiceOption(branch.createBranch("opt-" + i), { label: opt.label == null ? String(opt.value) : String(opt.label),
                                          note: opt.note ? String(opt.note) : null }, ROLE.CHOICE);
            css.addClass(option.el, pv_option);
            option.setOnClick(new ChoicePicked(self._field, String(opt.value)));
            list.appendChild(option.el);
            self._rows.push({ value: String(opt.value), option: option });
        });
        this._field.hold(list);
        this.root = this._field.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._mark(self._field.value()); });
        this._mark(this._field.value());
    }

    _mark(v) {
        this._rows.forEach(function (r) { r.option.selected = r.value === v; });
    }

    dispose() { this._stop(); this._field.dispose(); }
}
