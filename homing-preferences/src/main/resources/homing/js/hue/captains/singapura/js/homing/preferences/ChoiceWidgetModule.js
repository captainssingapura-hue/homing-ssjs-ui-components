// =============================================================================
// ChoiceWidget — one of a list. new ChoiceWidget(branch, params) → root, dispose
//   params: { name, label, summary?, default?, options: [{ value, label, note? }] }
// The options are a listbox; the one in force is aria-selected; a click or
// Enter/Space on an option writes it through the steward; it follows the
// store, as its field does, so the mark moves wherever the change came from.
// =============================================================================

const _choiceOwner = Object.freeze({ toString: () => "choiceWidget" });

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
            var row = branch.createElement("opt-" + i, "button");
            row.type = "button";
            css.addClass(row, pv_option);
            row.setAttribute("role", "option");
            var text = branch.createElement("opt-" + i + "-label", "span");
            text.textContent = opt.label == null ? String(opt.value) : String(opt.label);
            row.appendChild(text);
            if (opt.note) {
                var note = new Caption(CaptionFactory.create(branch, "opt-" + i + "-note"), { text: String(opt.note) }, ROLE.NOTE);
                css.addClass(note.el, pv_option_note);
                row.appendChild(note.el);
            }
            row.addEventListener("click", function () { self._field.set(String(opt.value)); });
            list.appendChild(row);
            self._rows.push({ value: String(opt.value), el: row });
        });
        this._field.hold(list);
        this.root = this._field.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._mark(self._field.value()); });
        this._mark(this._field.value());
    }

    _mark(v) {
        this._rows.forEach(function (r) { r.el.setAttribute("aria-selected", r.value === v ? "true" : "false"); });
    }

    dispose() { this._stop(); this._field.dispose(); }
}
