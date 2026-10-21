// =============================================================================
// Range — a value picked along a scale: the browser's own slider. A simple
// component on an <input type="range">, in the range's look, drawn in the colour
// its look gives it. Its value and its bounds are the element's; the user's
// dragging is the element's, dressed: the value moving its INPUT, let go its
// COMMIT.
//
//   var r = new Range(branch.elementMaker("range"), new FixedRange(), me.place(FixedRange));
//   r.min  r.max  r.step  r.value  r.enabled
//   r.setOnInput(handler)  r.setOnCommit(handler)  r.setOnEnabledChanged(handler)
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class Range extends SimpleComponent {
    static TAG = "input";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; },
            state: StateEnabled
        })
    });
    static EVENTS = Object.freeze([EventInput, EventCommit]);
    static BEHAVIOR = EditBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedRange, place ?? manageRange);
        this.el.type = "range";   // its bounds given first, so the value set is held to them
    }

    get min() { return Number(this.el.min); }
    set min(n) { if (n != null) this.el.min = String(n); }

    get max() { return Number(this.el.max); }
    set max(n) { if (n != null) this.el.max = String(n); }

    get step() { return Number(this.el.step); }
    set step(n) { if (n != null) this.el.step = String(n); }

    /** Where it stands on its scale: the element's. */
    get value() { return Number(this.el.value); }
    set value(n) { if (n != null) this.el.value = String(n); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its enabled changed - by a setter, or by nothing else an input has. */
    setOnEnabledChanged(handler) { this._setOn(StateEnabled, handler); }

    /** The value moving, the user still at it. */
    setOnInput(handler) { this._setOn(EventInput, handler); }

    /** The user done: let go. */
    setOnCommit(handler) { this._setOn(EventCommit, handler); }

    /** A word typed, as its behaviour hears it: its input. */
    _input(ev) { this._fire(EventInput, ev); }

    /** The user done, as its behaviour hears it: its commit. */
    _commit(ev) { this._fire(EventCommit, ev); }
}

