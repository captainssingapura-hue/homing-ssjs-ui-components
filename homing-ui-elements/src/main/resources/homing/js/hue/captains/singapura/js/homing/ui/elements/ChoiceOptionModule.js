// =============================================================================
// ChoiceOption — one choice of a setting, with a note on what it means. A
// simple component on its own <button role="option">, in the choice option's
// look, made on the branch its owner hands it: its name, and its note the
// house's caption cast as a note, at its end. The one picked (Selected,
// aria-selected), as its owner says; a press is its click. Where it sits is
// its owner's.
//
//   var opt = new ChoiceOption(branch.createBranch("opt-0"), { label: "Dark", note: "for the night", selected: true }, ROLE.CHOICE);
//   opt.el / opt.root  opt.label  opt.selected
//   opt.setOnClick(handler)  opt.setOnSelectedChanged(handler)
// =============================================================================

const _choiceOptionOwner = Object.freeze({ toString: () => "choiceOption" });

class ChoiceOption extends SimpleComponent {
    static LEAF = "choice-option";
    static TAG = "button";
    static AXES = Object.freeze({
        selected: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-selected", on ? "true" : "false"); },
            state: STATE.SELECTED
        })
    });
    static EVENTS = Object.freeze([EVENT.CLICK]);
    static WORDS = Object.freeze(["label"]);
    static BEHAVIOR = ItemBehavior;

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[ChoiceOption] a branch of its own is required");
        var p = Object.assign({}, props || {}), note = p.note;
        delete p.note;
        branch.activate(_choiceOptionOwner);
        super(branch.elementMaker("option"), p, role);
        this.el.type = "button";   // inside a form, a choice never submits it
        css.addClass(this.el, el_choice_option);
        this.el.setAttribute("role", "option");
        this._label = branch.createElement("label", "span");
        this.el.appendChild(this._label);
        if (note) {   // its note, the house's caption cast as a note: at its end
            this._note = new Caption(branch.elementMaker("note"), { text: String(note) }, ROLE.NOTE);
            css.addClass(this._note.el, el_choice_option_note);
            this.el.appendChild(this._note.el);
        }
        this.root = this.el;
        if (p.label != null) this.label = p.label;
    }

    get label() { return this._label ? this._label.textContent : ""; }
    set label(words) { if (this._label) this._label.textContent = words == null ? "" : String(words); }

    get selected() { return this._get("selected"); }
    set selected(on) { this._set("selected", !!on); }

    setOnClick(handler) { this._setOn(EVENT.CLICK, handler); }
    setOnSelectedChanged(handler) { this._setOn(STATE.SELECTED.changed, handler); }
}
