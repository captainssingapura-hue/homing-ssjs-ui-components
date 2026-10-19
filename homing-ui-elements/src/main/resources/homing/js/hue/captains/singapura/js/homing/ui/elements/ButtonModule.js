// =============================================================================
// Button — what the button leaves share, as declarations: the element they
// take, the state they have, the event they offer, the degree and the words,
// and the behaviour that is theirs (ButtonBehavior). A simple component
// (SimpleComponent) on the style manager (RFC 0071); never made on its own - a
// button is one of its leaves, each with the constructor every component has,
// its own fixed type and its own binding handed on. Its module is stamped with
// every leaf's header (D19), so the nodes it names - StateEnabled, EventClick,
// ExtentSize - are the duals':
//
//   var b = new DangerButton(branch.elementMaker("delete"), new FixedDangerButton(), me.slot(SLOT_…));
//   b.setOnClick(handler)          a press, while it is enabled: handler.handle(ComponentEvent)
//   b.setOnEnabledChanged(handler) its enabled changed: handler.handle(StateChange), previous the old value
//   b.enabled = false    off: inert, and it says so - the element disabled, and
//                        the state set through its reference, which the style draws
//   b.size = -1          −1 the smallest … 1 the biggest; 0 regular, the rest
//   b.label = "Delete"   the words on it
//   b.extent = 0.6       a coloured leaf's: how much of its word it wears, −1 the
//                        meaning turned the other way; the plain button has none
//
// A handler is an object of a class: canHandle(type) says it handles the event,
// handle(event) handles it. Set once, it stays.
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
        })
    });
    static EVENTS = Object.freeze([EventClick]);
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = ButtonBehavior;

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    get label() { return this.el.textContent; }
    set label(words) { this.el.textContent = words == null ? "" : String(words); }

    /** A press, while it is enabled. */
    setOnClick(handler) { this._setOn(EventClick, handler); }

    /** Its enabled changed - by a setter, or by nothing else a button has. */
    setOnEnabledChanged(handler) { this._setOn(StateEnabled, handler); }

    /** A press, as its behaviour hears it: its click, while it is enabled. */
    _pressed(ev) { if (this.enabled) this._fire(EventClick, ev); }
}
