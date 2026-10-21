// =============================================================================
// Select — a value picked from a list that drops down: the browser's own. A
// simple component on a <select>, in the select's look. Its options and its
// value are the element's: the options are written with the element's own
// operations, the list the browser opens is the browser's, and a pick made is
// the element's change, dressed as its COMMIT.
//
//   var s = new Select(branch.elementMaker("select"), new FixedSelect(), me.place(FixedSelect));
//   s.options   [{ value, label }], a bare word its own label
//   s.value  s.enabled  s.size
//   s.setOnCommit(handler)  s.setOnEnabledChanged(handler)
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class Select extends SimpleComponent {
    static TAG = "select";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; },
            state: StateEnabled
        })
    });
    static EVENTS = Object.freeze([EventCommit]);
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = PickBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedSelect, place ?? manageSelect); }

    /** What may be picked: the element's options, each its value and its label. */
    get options() { return Array.from(this.el.options).map(function (o) { return Object.freeze({ value: o.value, label: o.text }); }); }
    set options(list) {
        if (list == null) return;
        var el = this.el;
        el.options.length = 0;   // the element's own: its options cleared
        list.forEach(function (o) {
            var value = o !== null && typeof o === "object" ? o.value : o, label = o !== null && typeof o === "object" && o.label != null ? o.label : value;
            el.add(new Option(String(label), String(value)));   // the element's own: an option added
        });
    }

    /** What is picked: the element's. */
    get value() { return this.el.value; }
    set value(v) { if (v != null) this.el.value = String(v); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its enabled changed - by a setter, or by nothing else an input has. */
    setOnEnabledChanged(handler) { this._setOn(StateEnabled, handler); }

    /** A pick made. */
    setOnCommit(handler) { this._setOn(EventCommit, handler); }

    /** A pick made, as its behaviour hears it: its commit. */
    _commit(ev) { this._fire(EventCommit, ev); }
}

