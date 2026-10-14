// =============================================================================
// Lamp — A light that says how something is: quiet at rest, lit while what it watches is where the user is
// (Current, aria-current), broken while what it watches is refused (Invalid, aria-invalid) - its words its
// own. A simple component on a <div>, in the lamp's look. Nothing happens on a press.
//
//   var lamp = new Lamp(branch.elementMaker("lamp"), { text: "held by the grid" }, ROLE.BODY);
//   lamp.el  lamp.text  lamp.current  lamp.invalid
//   lamp.setOnCurrentChanged(handler)  lamp.setOnInvalidChanged(handler)
// =============================================================================

class Lamp extends SimpleComponent {
    static LEAF = "lamp";
    static TAG = "div";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: STATE.CURRENT
        }),
        invalid: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-invalid", "true"); else el.removeAttribute("aria-invalid"); },
            state: STATE.INVALID
        })
    });
    static WORDS = Object.freeze(["text"]);
    static DEGREES = Object.freeze(["size"]);
    static BEHAVIOR = MarkBehavior;

    /** Its words. */
    get text() { return this.el.textContent; }
    set text(words) { if (words != null) this.el.textContent = String(words); }

    /** Lit: what it watches is where the user is. */
    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** Broken: what it watches is refused. */
    get invalid() { return this._get("invalid"); }
    set invalid(on) { this._set("invalid", !!on); }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    setOnCurrentChanged(handler) { this._setOn(STATE.CURRENT.changed, handler); }
    setOnInvalidChanged(handler) { this._setOn(STATE.INVALID.changed, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const LampFactory = new SimpleComponentFactory(Lamp);
