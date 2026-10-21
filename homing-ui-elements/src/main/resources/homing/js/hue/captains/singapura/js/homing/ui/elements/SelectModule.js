// =============================================================================
// Select — a value picked from a list that drops down: the browser's own. A
// simple component on a <select>, in the select's look. Its options and its
// value are the element's: the options are written with the element's own
// operations, the list the browser opens is the browser's, and the option
// chosen - the element's value, by a pick or a setter - is its chosen option.
//
//   var s = new Select(branch.elementMaker("select"), new FixedSelect(), me.place(FixedSelect));
//   s.options   [{ value, label }], a bare word its own label
//   s.value  s.chosen  s.enabled  s.size
//   s.onChange(StateChosenOption, listener)  s.onChange(StateEnabled, listener)
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
        }),
        chosen: Object.freeze({ rest: "", state: StateChosenOption })   // the element's value, as a pick or a setter leaves it; no class
    });
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
        this._change("chosen", el.value);   // the element chooses its first, or none: its chosen with it
    }

    /** What is picked: the element's. */
    get value() { return this.el.value; }
    set value(v) {
        if (v == null) return;
        this.el.value = String(v);
        this._change("chosen", this.el.value);   // none, where it offers no such option
    }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** The option chosen, by its value: none at rest. */
    get chosen() { return this._get("chosen"); }

    /** A pick made, as its behaviour hears it: the option it chose. */
    _picked() { this._change("chosen", this.el.value); }
}

