// =============================================================================
// SettingRow — one setting of a group, listed with its value: the preferences'
// own leaf. A simple component on a <div role="listitem">, in the setting
// row's look, made on the branch its owner hands it: its name, and its value
// the house's caption cast as a value, at its end. It is still: nothing
// happens on a press. The line between rows is a separator its list places.
//
//   var row = new SettingRow(branch.createBranch("s-0"), { label: "Theme", value: "default" }, ROLE.ENTRY);
//   row.el / row.root  row.label  row.value
// =============================================================================

const _settingRowOwner = Object.freeze({ toString: () => "settingRow" });

class SettingRow extends SimpleComponent {
    static LEAF = "setting-row";
    static TAG = "div";
    static WORDS = Object.freeze(["label", "value"]);
    static BEHAVIOR = MarkBehavior;

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[SettingRow] a branch of its own is required");
        branch.activate(_settingRowOwner);
        super(branch.elementMaker("row"), props, role);
        css.addClass(this.el, pv_setting_row);
        this.el.setAttribute("role", "listitem");
        this._label = branch.createElement("label", "span");
        this.el.appendChild(this._label);
        // its value, the house's caption cast as a value: at its end
        this._value = new Caption(branch.elementMaker("value"), {}, ROLE.VALUE);
        css.addClass(this._value.el, pv_setting_row_value);
        this.el.appendChild(this._value.el);
        this.root = this.el;
        var p = props || {};
        if (p.label != null) this.label = p.label;
        if (p.value != null) this.value = p.value;
    }

    get label() { return this._label ? this._label.textContent : ""; }
    set label(words) { if (this._label) this._label.textContent = words == null ? "" : String(words); }

    /** Its value, as its caption's words. */
    get value() { return this._value ? this._value.text : ""; }
    set value(words) { if (this._value) this._value.text = words == null ? "" : String(words); }
}
