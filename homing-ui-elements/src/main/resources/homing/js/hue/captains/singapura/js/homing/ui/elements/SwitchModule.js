// =============================================================================
// Switch — a setting on or off: a knob sliding in a groove, the whole a control.
// A composed component on a <button> (role switch), on the style manager (RFC
// 0071): it takes a branch of its own and makes on it its button and its parts,
// the house's - its groove (a ToggleGroove, as its span) and its knob in it (a
// ToggleKnob, as its thumb) - each placed along its slot by its own reference,
// and its words beside them. Its state rides on its button: on is aria-checked,
// off is disabled, each set through its reference; a press turns it - its
// groove turning with it - and is its COMMIT.
//
//   var s = new Switch(branch.createBranch("dark"), new FixedSwitch(), me.slot(SLOT_…));
//   s.root                           the <button>
//   s.checked  s.enabled  s.text     its words beside the groove
//   s.setOnCheckedChanged(handler)   turned, by a press (the DOM event carried) or a setter
//   s.setOnCommit(handler)           the user done: a press
// =============================================================================

const _switchOwner = Object.freeze({ toString: () => "switch" });

class Switch extends SimpleComponent {
    static TAG = "button";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; },
            state: StateEnabled
        }),
        checked: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-checked", on ? "true" : "false"); },
            state: StateChecked
        })
    });
    static EVENTS = Object.freeze([EventCommit]);
    static BEHAVIOR = TurnBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[Switch] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_switchOwner);
        super(branch.elementMaker("switch"), fixed, FixedSwitch, place ?? manageSwitch);
        this.el.type = "button";
        this.el.setAttribute("role", "switch");
        css.addClass(this.el, el_switch);
        this._groove = new ToggleGroove(branch.elementMaker("groove"), new FixedToggleGroove(), this._slot(SLOT_SWITCH_TOGGLE_GROOVE_SPAN));
        css.addClass(this._groove.el, el_switch_groove);
        var knob = new ToggleKnob(branch.elementMaker("knob"), new FixedToggleKnob(), this._slot(SLOT_SWITCH_TOGGLE_KNOB_THUMB));
        css.addClass(knob.el, el_switch_knob);
        this._groove.el.appendChild(knob.el);
        this.el.appendChild(this._groove.el);
        this._word = branch.createElement("word", "span");
        this.el.appendChild(this._word);
        this.root = this.el;
    }

    get checked() { return this._get("checked"); }
    set checked(on) { this._set("checked", !!on); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its words beside its groove: what it says it is. */
    get text() { return this._word.textContent; }
    set text(words) { this._word.textContent = words == null ? "" : String(words); }

    /** Turned, by a press or a setter. */
    setOnCheckedChanged(handler) { this._setOn(StateChecked, handler); }

    /** The user done: a press. */
    setOnCommit(handler) { this._setOn(EventCommit, handler); }

    /** Its enabled changed - by a setter, or by nothing else a switch has. */
    setOnEnabledChanged(handler) { this._setOn(StateEnabled, handler); }

    /** A press, as its behaviour hears it: while it is enabled, turned - its groove with it - then its commit. */
    _turned(ev) {
        if (!this.enabled) return;
        this._change("checked", !this.checked, ev);
        this._fire(EventCommit, ev);
    }
}
