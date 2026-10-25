// =============================================================================
// Button — what the button leaves share, as declarations: the element they
// take, the states they have, the degree and the words,
// and the behaviour that is theirs (ButtonBehavior). A simple component
// (SimpleComponent) on the style manager (RFC 0071); never made on its own - a
// button is one of its leaves, each with the constructor every component has,
// its own fixed type and its own binding handed on. Its module is stamped with
// every leaf's header (D19), so the nodes it names - StateEnabled, StateClicks,
// ExtentSize - are the duals':
//
//   var b = new DangerButton(branch.elementMaker("delete"), new FixedDangerButton(), me.place(FixedDangerButton));
//   b.onChange(StateClicks, listener)  a press, while it is enabled: its clicks counted up -
//                        listener.handle(StateChange), the count before and after
//   b.onChange(StateEnabled, listener) its enabled changed: listener.handle(StateChange), its value before and after
//   b.clicks             how many times it was pressed, from none
//   b.enabled = false    off: inert, and it says so - the element disabled, and
//                        the state set through its reference, which the style draws
//   b.size = -1          −1 the smallest … 1 the biggest; 0 regular, the rest
//   b.label = "Delete"   the words on it
//   b.extent = 0.6       a coloured leaf's: how much of its word it wears, −1 the
//                        meaning turned the other way; the plain button has none
//
// A listener is an object of a class: canHandle(type) says it hears the state,
// handle(change) hears it. Set once, it stays.
//
// What a button looks like is its style's answer for its casting, never a word
// it names: the leaf declares what it is, and the style manager puts on the
// class of its casting.
// =============================================================================

class Button extends SimpleComponent {
    static TAG = "button";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; },
            state: StateEnabled
        }),
        clicks: Object.freeze({ rest: 0, state: StateClicks })   // counted up by its behaviour, never set: no class
    });
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = ButtonBehavior;

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** How many times it was pressed, from none: a click is its going up. */
    get clicks() { return this._get("clicks"); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    get label() { return this.el.textContent; }
    set label(words) { this.el.textContent = words == null ? "" : String(words); }

    /** A press, as its behaviour hears it: while it is enabled, its clicks counted up. */
    _pressed() { if (this.enabled) this._change("clicks", this.clicks + 1); }
}
