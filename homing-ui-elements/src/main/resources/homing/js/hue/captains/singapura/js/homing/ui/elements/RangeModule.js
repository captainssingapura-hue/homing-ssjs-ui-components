// =============================================================================
// Range — the browser's own slider, wrapped: a degree picked along it. A simple
// component on an <input type="range">, in the range's look. The native element
// knows nothing of the states: the range gives it a degree's bounds, −1 to 1,
// and the step its caller sets, and what the hand does on it is the range's two
// states - its degree, as the knob moves or a key moves it, and Dragging while
// the hand holds it (RFC 0069, ranges case by case). What a degree is read as - a
// volume, a gain, a font size - is its caller's, through a DegreeScale.
//
//   var r = new Range(branch.elementMaker("range"), new FixedRange(), me.place(FixedRange));
//   r.degree = 0.2      where it stands, −1 … 1; 0 at rest - held to its step
//   r.step = 0.1        how far it moves at once, as a degree; any, unless set
//   r.dragging  r.enabled
//   r.onChange(StateDegree, listener)     moved: by the hand, a key or a setter
//   r.onChange(StateDragging, listener)   taken by the hand, and let go
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
        }),
        degree: Object.freeze({ rest: 0, state: StateDegree }),   // the knob where it stands: no class
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            state: StateDragging   // its class while the hand holds it
        })
    });
    static BEHAVIOR = SlideBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedRange, place ?? manageRange);
        this.el.type = "range";
        this.el.min = "-1";   // a degree's bounds, the element's own: never its caller's
        this.el.max = "1";
        this.el.step = "any";
        this.el.value = String(this.degree);
    }

    /** Where it stands, −1 … 1: set, it is held to its step, as the element holds it. */
    get degree() { return this._get("degree"); }
    set degree(d) {
        var n = Number(d);
        if (!StateDegree.allows(n)) throw new Error("[Range] a degree is -1 to 1, not " + d);
        this.el.value = String(n);
        this._change("degree", Number(this.el.value));
    }

    /** How far it moves at once, as a degree - its caller's, never its type's: any, unless set. A new step holds it to the step. */
    get step() { return this.el.step === "any" ? null : Number(this.el.step); }
    set step(s) {
        if (s == null) this.el.step = "any";
        else {
            var n = Number(s);
            if (!(n > 0 && n <= 2)) throw new Error("[Range] a step is a degree's, above 0 and at most 2, not " + s);
            this.el.step = String(n);
        }
        this._change("degree", Number(this.el.value));
    }

    /** Whether the hand holds it: its behaviour's to say. */
    get dragging() { return this._get("dragging"); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Moved, by the hand or a key, as its behaviour hears it: its degree, where the element now stands. */
    _moved() { this._change("degree", Number(this.el.value)); }

    /** Taken by the hand, as its behaviour hears it: dragging, while it is enabled. */
    _taken() { if (this.enabled) this._change("dragging", true); }

    /** Let go, wherever the hand is, as its behaviour hears it: still. */
    _let() { this._change("dragging", false); }
}
