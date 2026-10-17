// =============================================================================
// Range — a value picked along a scale: the browser's own slider. A simple
// component on an <input type="range">, in the range's look, drawn in the colour
// its look gives it. Its value and its bounds are the element's; the user's
// dragging is the element's, dressed: the value moving its INPUT, let go its
// COMMIT.
//
//   var r = new Range(RangeFactory.create(branch, "volume"), { min: 0, max: 100, value: 40 }, ROLE.STANDALONE);
//   r.min  r.max  r.step  r.value  r.enabled
//   r.setOnInput(handler)  r.setOnCommit(handler)  r.setOnEnabledChanged(handler)
// =============================================================================

class Range extends SimpleComponent {
    static LEAF = "range";
    static TAG = "input";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; },
            state: STATE.ENABLED
        })
    });
    static EVENTS = Object.freeze([EVENT.INPUT, EVENT.COMMIT]);
    static WORDS = Object.freeze(["min", "max", "step", "value"]);
    static BEHAVIOR = EditBehavior;

    constructor(make, props, role) {
        super(make, props, role);
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
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }

    /** The value moving, the user still at it. */
    setOnInput(handler) { this._setOn(EVENT.INPUT, handler); }

    /** The user done: let go. */
    setOnCommit(handler) { this._setOn(EVENT.COMMIT, handler); }
}

/** Its companion: makes the <input> it is realized on, on its owner's branch, under its owner's name. */
const RangeFactory = new SimpleComponentFactory(Range);
