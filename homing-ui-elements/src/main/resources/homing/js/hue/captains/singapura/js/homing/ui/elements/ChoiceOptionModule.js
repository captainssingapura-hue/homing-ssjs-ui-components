// =============================================================================
// ChoiceOption — one choice of a setting, with a note on what it means. A
// composed component on its own <button role="option">, on the style manager
// (RFC 0071): it takes a branch of its own and makes on it its button, its name,
// and - the first time one is given - its note, the house's caption cast as a
// note, at its end, placed along its slot by its own reference. The one picked
// (Selected, aria-selected), as its owner says, set through its reference; a
// press is its click. Where it sits is its owner's.
//
//   var opt = new ChoiceOption(branch.createBranch("opt-0"), new FixedChoiceOption(), me.slot(SLOT_…));
//   opt.label = "Dark"   opt.note = "for the night"   opt.selected = true
//   opt.el / opt.root
//   opt.setOnClick(handler)  opt.setOnSelectedChanged(handler)
// =============================================================================

const _choiceOptionOwner = Object.freeze({ toString: () => "choiceOption" });

class ChoiceOption extends SimpleComponent {
    static TAG = "button";
    static AXES = Object.freeze({
        selected: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-selected", on ? "true" : "false"); },
            state: StateSelected
        })
    });
    static EVENTS = Object.freeze([EventClick]);
    static BEHAVIOR = ItemBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[ChoiceOption] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_choiceOptionOwner);
        super(branch.elementMaker("option"), fixed, FixedChoiceOption, place ?? manageChoiceOption);
        this.branch = branch;
        this.el.type = "button";   // inside a form, a choice never submits it
        css.addClass(this.el, el_choice_option);
        this.el.setAttribute("role", "option");
        this._label = branch.createElement("label", "span");
        this.el.appendChild(this._label);
        this._note = null;
        this.root = this.el;
    }

    get label() { return this._label.textContent; }
    set label(words) { this._label.textContent = words == null ? "" : String(words); }

    /** Its note: the caption made the first time one is given. */
    get note() { return this._note ? this._note.text : ""; }
    set note(words) {
        if (words == null || words === "") { if (this._note) this._note.text = ""; return; }
        if (!this._note) {   // its note, the house's caption cast as a note: at its end
            this._note = new Caption(this.branch.elementMaker("note"), new FixedCaption(), this._slot(SLOT_CAPTION_NOTE));
            css.addClass(this._note.el, el_choice_option_note);
            this.el.appendChild(this._note.el);
        }
        this._note.text = String(words);
    }

    get selected() { return this._get("selected"); }
    set selected(on) { this._set("selected", !!on); }

    setOnClick(handler) { this._setOn(EventClick, handler); }
    setOnSelectedChanged(handler) { this._setOn(StateSelected, handler); }

    /** A press, as its behaviour hears it. */
    _clicked(ev) { this._fire(EventClick, ev); }
}
