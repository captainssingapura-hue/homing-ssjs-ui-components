// =============================================================================
// Checkbox — one thing on or off, by a mark in a box: the browser's own. A
// simple component on an <input type="checkbox">, in the checkbox's look, its
// mark in the colour its look gives it. The tick is the element's: the user
// turns it on the element, and the component takes it as its checked, its
// Checked state's class held while it holds; a setter turns the element's.
//
//   var c = new Checkbox(branch.elementMaker("checkbox"), new FixedCheckbox(), me.slot(SLOT_…));
//   c.checked  c.enabled
//   c.setOnCheckedChanged(handler)   by the user (the DOM event carried) or a setter
//   c.setOnCommit(handler)           the user done: a tick turned
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class Checkbox extends SimpleComponent {
    static TAG = "input";
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
            reflect: function (el, on) { el.checked = on; },
            state: StateChecked
        })
    });
    static EVENTS = Object.freeze([EventCommit]);
    static BEHAVIOR = CheckBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedCheckbox, place ?? manageCheckbox);
        this.el.type = "checkbox";
        css.addClass(this.el, el_checkbox);   // the browser's box, laid out: no margin of its own
    }

    get checked() { return this._get("checked"); }
    set checked(on) { this._set("checked", !!on); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its enabled changed - by a setter, or by nothing else an input has. */
    setOnEnabledChanged(handler) { this._setOn(StateEnabled, handler); }

    /** Its tick turned, by the user or a setter. */
    setOnCheckedChanged(handler) { this._setOn(StateChecked, handler); }

    /** The user done: a tick turned on the element. */
    setOnCommit(handler) { this._setOn(EventCommit, handler); }

    /** A tick turned on the element, as its behaviour hears it: its checked, then its commit. */
    _ticked(on, ev) {
        this._change("checked", on, ev);
        this._fire(EventCommit, ev);
    }
}

