// =============================================================================
// Select — a value picked from a list that drops down: the browser's own. A
// simple component on a <select>, in the select's look. Its options and its
// value are the element's: the options are written with the element's own
// operations, the list the browser opens is the browser's, and a pick made is
// the element's change, dressed as its COMMIT.
//
//   var s = new Select(SelectFactory.create(branch, "style"), { options: [{ value: "a", label: "A" }, "b"], value: "a" }, ROLE.STANDALONE);
//   s.options   [{ value, label }], a bare word its own label
//   s.value  s.enabled  s.size
//   s.setOnCommit(handler)  s.setOnEnabledChanged(handler)
// =============================================================================

class Select extends SimpleComponent {
    static LEAF = "select";
    static TAG = "select";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; },
            state: STATE.ENABLED
        })
    });
    static EVENTS = Object.freeze([EVENT.COMMIT]);
    static DEGREES = Object.freeze(["size"]);
    static WORDS = Object.freeze(["options", "value"]);
    static BEHAVIOR = PickBehavior;

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

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its enabled changed - by a setter, or by nothing else an input has. */
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }

    /** A pick made. */
    setOnCommit(handler) { this._setOn(EVENT.COMMIT, handler); }
}

/** Its companion: makes the <select> it is realized on, on its owner's branch, under its owner's name. */
const SelectFactory = new SimpleComponentFactory(Select);
