// =============================================================================
// Switch — a setting on or off: a knob sliding in a groove, the whole a control.
// A simple component on a <button> (role switch), in the switch's look, made on
// the branch its owner hands it, with its parts the house's: its groove (a
// ToggleGroove, as its span) and its knob in it (a ToggleKnob, as its thumb),
// and its words beside them. Its state rides on its button: on is
// aria-checked, off is disabled; a press turns it - its groove turning with it
// - dressed as its COMMIT.
//
//   var s = new Switch(branch.createBranch("dark"), { checked: false, text: "Off" }, ROLE.TOGGLE);
//   s.root                           the <button>
//   s.checked  s.enabled  s.text     its words beside the groove
//   s.setOnCheckedChanged(handler)   turned, by a press (the DOM event carried) or a setter
//   s.setOnCommit(handler)           the user done: a press
// =============================================================================

const _switchOwner = Object.freeze({ toString: () => "switch" });

class Switch extends SimpleComponent {
    static LEAF = "switch";
    static TAG = "button";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; },
            state: STATE.ENABLED
        }),
        checked: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-checked", on ? "true" : "false"); },
            state: STATE.CHECKED
        })
    });
    static EVENTS = Object.freeze([EVENT.COMMIT]);
    static WORDS = Object.freeze(["text"]);
    static BEHAVIOR = TurnBehavior;

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[Switch] a branch of its own is required");
        branch.activate(_switchOwner);
        super(branch.elementMaker("switch"), props, role);
        this.el.type = "button";
        this.el.setAttribute("role", "switch");
        css.addClass(this.el, el_switch);
        this._groove = new ToggleGroove(branch.elementMaker("groove"), { checked: this.checked }, ROLE.SPAN);
        css.addClass(this._groove.el, el_switch_groove);
        var knob = new ToggleKnob(branch.elementMaker("knob"), {}, ROLE.THUMB);
        css.addClass(knob.el, el_switch_knob);
        this._groove.el.appendChild(knob.el);
        this.el.appendChild(this._groove.el);
        this._word = branch.createElement("word", "span");
        this.el.appendChild(this._word);
        this.root = this.el;
        if (props && props.text != null) this.text = props.text;
    }

    get checked() { return this._get("checked"); }
    set checked(on) { this._set("checked", !!on); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its words beside its groove: what it says it is. */
    get text() { return this._word ? this._word.textContent : ""; }
    set text(words) { if (this._word) this._look.text(this._word, words == null ? "" : String(words)); }

    /** Turned, by a press or a setter. */
    setOnCheckedChanged(handler) { this._setOn(STATE.CHECKED.changed, handler); }

    /** The user done: a press. */
    setOnCommit(handler) { this._setOn(EVENT.COMMIT, handler); }

    /** Its enabled changed - by a setter, or by nothing else a switch has. */
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }
}
