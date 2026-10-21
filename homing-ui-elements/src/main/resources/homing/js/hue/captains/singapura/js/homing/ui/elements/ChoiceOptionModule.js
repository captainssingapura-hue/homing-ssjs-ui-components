// =============================================================================
// ChoiceOption — one choice of a setting, with a note on what it means. A
// composed component on its own <button role="option">, on the style manager
// (RFC 0071): it takes a branch of its own and makes on it its button, its name,
// and its note where it has one - the house's caption cast as a note, at its
// end, placed along its slot by its own reference. Whether it has a note is
// static (RFC 0071 port, question 5): its fixed object holds the note's own, or
// null, and the note is made with the choice, for its life. The one picked
// (Selected, aria-selected), as its owner says, set through its reference; a
// press is its click. Where it sits is its owner's.
//
//   var opt = new ChoiceOption(branch.createBranch("opt-0"), new FixedChoiceOption(new FixedCaption()), me.slot(SLOT_…));
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
        if (this.fixed.note !== null) {   // its note, the house's caption cast as a note: at its end
            this._note = new Caption(branch.elementMaker("note"), this.fixed.note, this._slot(SLOT_CHOICE_OPTION_CAPTION_NOTE));
            css.addClass(this._note.el, el_choice_option_note);
            this.el.appendChild(this._note.el);
        }
        this.root = this.el;
    }

    get label() { return this._label.textContent; }
    set label(words) { this._label.textContent = words == null ? "" : String(words); }

    /** Its note's words, where it has a note. */
    get note() { return this._note ? this._note.text : ""; }
    set note(words) {
        if (!this._note) throw new TypeError("[ChoiceOption] made with no note: whether it has one is fixed for its life");
        this._note.text = words == null ? "" : String(words);
    }

    get selected() { return this._get("selected"); }
    set selected(on) { this._set("selected", !!on); }

    setOnClick(handler) { this._setOn(EventClick, handler); }
    setOnSelectedChanged(handler) { this._setOn(StateSelected, handler); }

    /** A press, as its behaviour hears it. */
    _clicked(ev) { this._fire(EventClick, ev); }
}
