// =============================================================================
// SettingRow — one setting of a list of them: its name, and its value at its
// end. A composed component on the style manager (RFC 0071): it takes a branch
// of its own and makes on it its row, its name, and its value - the house's
// caption cast as a value - placed along its slot by its own reference.
//
//   var row = new SettingRow(branch.createBranch("row-theme"), new FixedSettingRow(), me.slot(SLOT_…));
//   row.label = "Theme"   row.value = "Harbour"   row.root
// =============================================================================

const _settingRowOwner = Object.freeze({ toString: () => "settingRow" });

class SettingRow extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[SettingRow] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_settingRowOwner);
        super(branch.elementMaker("row"), fixed, FixedSettingRow, place ?? manageSettingRow);
        css.addClass(this.el, pv_setting_row);
        this.el.setAttribute("role", "listitem");
        this._label = branch.createElement("label", "span");
        this.el.appendChild(this._label);
        // its value, the house's caption cast as a value: at its end
        this._value = new Caption(branch.elementMaker("value"), new FixedCaption(), this._slot(SLOT_CAPTION_VALUE));
        css.addClass(this._value.el, pv_setting_row_value);
        this.el.appendChild(this._value.el);
        this.root = this.el;
    }

    get label() { return this._label.textContent; }
    set label(words) { this._label.textContent = words == null ? "" : String(words); }

    /** Its value, as its caption's words. */
    get value() { return this._value.text; }
    set value(words) { this._value.text = words == null ? "" : String(words); }
}
