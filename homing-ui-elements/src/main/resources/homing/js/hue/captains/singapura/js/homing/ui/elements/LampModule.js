// =============================================================================
// Lamp — A light that says how something is: quiet at rest, lit while what it watches is where the user is
// (Current, aria-current), broken while what it watches is refused (Invalid, aria-invalid) - its words its
// own. A simple component on a <div>. Nothing happens on a press.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var lamp = new Lamp(branch.elementMaker("lamp"), new FixedLamp(), me.slot(SLOT_…));
//   lamp.text  lamp.current  lamp.invalid
//   lamp.setOnCurrentChanged(handler)  lamp.setOnInvalidChanged(handler)
// =============================================================================

class Lamp extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: StateCurrent
        }),
        invalid: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-invalid", "true"); else el.removeAttribute("aria-invalid"); },
            state: StateInvalid
        })
    });
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedLamp, place ?? manageLamp); }

    /** Its words. */
    get text() { return this.el.textContent; }
    set text(words) { this.el.textContent = words == null ? "" : String(words); }

    /** Lit: what it watches is where the user is. */
    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** Broken: what it watches is refused. */
    get invalid() { return this._get("invalid"); }
    set invalid(on) { this._set("invalid", !!on); }

    setOnCurrentChanged(handler) { this._setOn(StateCurrent, handler); }
    setOnInvalidChanged(handler) { this._setOn(StateInvalid, handler); }
}
