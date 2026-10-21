// =============================================================================
// TextBox — words the user types, on one line: the browser's own editing. A
// simple component on an <input type="text">, in the text box's look. Its value
// is the element's - the user's typing is the element's, never copied - and its
// events are the element's, dressed: a word typed its INPUT, the user done with
// it (the element's change) its COMMIT.
//
//   var t = new TextBox(branch.elementMaker("text-box"), new FixedTextBox(), me.place(FixedTextBox));
//   t.value  t.placeholder  t.enabled  t.size
//   t.setOnInput(handler)  t.setOnCommit(handler)  t.onChange(StateEnabled, listener)
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class TextBox extends SimpleComponent {
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
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = EditBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedTextBox, place ?? manageTextBox);
        this.el.type = "text";
    }

    /** What is typed in it: the element's. */
    get value() { return this.el.value; }
    set value(words) { if (words != null) this.el.value = String(words); }

    /** What it says while it is empty: the element's. */
    get placeholder() { return this.el.placeholder; }
    set placeholder(words) { if (words != null) this.el.placeholder = String(words); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** A word typed, the user still at it. */
    setOnInput(handler) { this._setOn(EventInput, handler); }

    /** The user done with its value. */
    setOnCommit(handler) { this._setOn(EventCommit, handler); }

    /** A word typed, as its behaviour hears it: its input. */
    _input(ev) { this._fire(EventInput, ev); }

    /** The user done, as its behaviour hears it: its commit. */
    _commit(ev) { this._fire(EventCommit, ev); }
}

