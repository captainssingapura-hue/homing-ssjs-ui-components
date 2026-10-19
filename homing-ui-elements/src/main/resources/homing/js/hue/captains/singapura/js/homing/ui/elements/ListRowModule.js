// =============================================================================
// ListRow — one row of a list. A simple component on a <div>, in the list
// row's look; what it shows is its owner's - its words, or the elements its
// owner appends. Picked from its list (Selected, aria-selected); the one that
// is where the user is (Current, aria-current); and where the page's keys
// are, as its owner tells it (Keyboard: away, candidate, lent or held,
// data-keys) - a row that stands for a member of the keyboard party, in a
// monitor of it. A press is its click. Where it sits, and how deep, is its
// list's.
//
//   var row = new ListRow(branch.elementMaker("list-row"), new FixedListRow(), me.slot(SLOT_…));
//   row.el  row.text  row.selected  row.current  row.keys
//   row.setOnClick(handler)  row.setOnSelectedChanged(handler)  row.setOnCurrentChanged(handler)  row.setOnKeyboardChanged(handler)
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class ListRow extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        selected: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-selected", on ? "true" : "false"); },
            state: StateSelected
        }),
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: StateCurrent
        }),
        keys: Object.freeze({
            values: StateKeyboard.states,
            rest: StateKeyboard.rest,
            reflect: function (el, at) { if (at === StateKeyboard.rest) el.removeAttribute("data-keys"); else el.setAttribute("data-keys", at); },
            state: StateKeyboard
        })
    });
    static EVENTS = Object.freeze([EventClick]);
    static BEHAVIOR = ItemBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedListRow, place ?? manageListRow); }

    /** Its words, when it shows words. */
    get text() { return this.el.textContent; }
    set text(words) { if (words != null) this.el.textContent = String(words); }

    get selected() { return this._get("selected"); }
    set selected(on) { this._set("selected", !!on); }

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** Where the page's keys are, for what the row stands for: away, candidate, lent or held. */
    get keys() { return this._get("keys"); }
    set keys(at) { this._set("keys", at == null ? StateKeyboard.rest : String(at)); }

    setOnClick(handler) { this._setOn(EventClick, handler); }

    /** A click, as its behaviour hears it, while it may be clicked. */
    _clicked(ev) { this._fire(EventClick, ev); }
    setOnSelectedChanged(handler) { this._setOn(StateSelected, handler); }
    setOnCurrentChanged(handler) { this._setOn(StateCurrent, handler); }
    setOnKeyboardChanged(handler) { this._setOn(StateKeyboard, handler); }
}

