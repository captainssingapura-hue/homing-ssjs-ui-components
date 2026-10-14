// =============================================================================
// TextBox — words the user types, on one line: the browser's own editing. A
// simple component on an <input type="text">, in the text box's look. Its value
// is the element's - the user's typing is the element's, never copied - and its
// events are the element's, dressed: a word typed its INPUT, the user done with
// it (the element's change) its COMMIT.
//
//   var t = new TextBox(TextBoxFactory.create(branch, "name"), { value: "…", placeholder: "Your name" }, ROLE.STANDALONE);
//   t.value  t.placeholder  t.enabled  t.size
//   t.setOnInput(handler)  t.setOnCommit(handler)  t.setOnEnabledChanged(handler)
// =============================================================================

class TextBox extends SimpleComponent {
    static LEAF = "text-box";
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
    static DEGREES = Object.freeze(["size"]);
    static WORDS = Object.freeze(["value", "placeholder"]);
    static BEHAVIOR = EditBehavior;

    constructor(make, props, role) {
        super(make, props, role);
        this.el.type = "text";
    }

    /** What is typed in it: the element's. */
    get value() { return this.el.value; }
    set value(words) { if (words != null) this.el.value = String(words); }

    /** What it says while it is empty: the element's. */
    get placeholder() { return this.el.placeholder; }
    set placeholder(words) { if (words != null) this.el.placeholder = String(words); }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** Its enabled changed - by a setter, or by nothing else an input has. */
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }

    /** A word typed, the user still at it. */
    setOnInput(handler) { this._setOn(EVENT.INPUT, handler); }

    /** The user done with its value. */
    setOnCommit(handler) { this._setOn(EVENT.COMMIT, handler); }
}

/** Its companion: makes the <input> it is realized on, on its owner's branch, under its owner's name. */
const TextBoxFactory = new SimpleComponentFactory(TextBox);
