// =============================================================================
// TextBox — words the user types, on one line: the browser's own editing. A
// simple component on an <input type="text">, in the text box's look. Its value
// is the element's - the user's typing is the element's - and what it holds is
// two of its states: a word typed is its draft's change, the user done with it
// (the element's change: Enter, or leaving it) its committed text's.
//
//   var t = new TextBox(branch.elementMaker("text-box"), new FixedTextBox(), me.place(FixedTextBox));
//   t.value  t.draft  t.committed  t.placeholder  t.enabled  t.size
//   t.onChange(StateDraftText, listener)  t.onChange(StateCommittedText, listener)  t.onChange(StateEnabled, listener)
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
        }),
        draft: Object.freeze({ rest: "", state: StateDraftText }),           // what is typed so far; no class
        committed: Object.freeze({ rest: "", state: StateCommittedText })    // what the user is done typing; no class
    });
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = EditBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedTextBox, place ?? manageTextBox);
        this.el.type = "text";
    }

    /** What is typed in it: the element's. */
    get value() { return this.el.value; }
    set value(words) {
        if (words == null) return;
        this.el.value = String(words);
        this._change("draft", this.el.value);   // set, it is both what is typed and what is done
        this._change("committed", this.el.value);
    }

    /** What it says while it is empty: the element's. */
    get placeholder() { return this.el.placeholder; }
    set placeholder(words) { if (words != null) this.el.placeholder = String(words); }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** What is typed so far: none at rest. */
    get draft() { return this._get("draft"); }

    /** What the user is done typing: none at rest. */
    get committed() { return this._get("committed"); }

    /** A word typed, as its behaviour hears it: its draft. */
    _input() { this._change("draft", this.el.value); }

    /** The user done, as its behaviour hears it: its committed text. */
    _commit() { this._change("committed", this.el.value); }
}

