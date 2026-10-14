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
//   var row = new ListRow(branch.elementMaker("row-0"), { text: "Appearance", selected: false }, ROLE.CHOICE);
//   row.el  row.text  row.selected  row.current  row.keys
//   row.setOnClick(handler)  row.setOnSelectedChanged(handler)  row.setOnCurrentChanged(handler)  row.setOnKeyboardChanged(handler)
// =============================================================================

class ListRow extends SimpleComponent {
    static LEAF = "list-row";
    static TAG = "div";
    static AXES = Object.freeze({
        selected: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-selected", on ? "true" : "false"); },
            state: STATE.SELECTED
        }),
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: STATE.CURRENT
        }),
        keys: Object.freeze({
            values: STATE.KEYBOARD.values,
            rest: STATE.KEYBOARD.rest,
            reflect: function (el, at) { if (at === STATE.KEYBOARD.rest) el.removeAttribute("data-keys"); else el.setAttribute("data-keys", at); },
            state: STATE.KEYBOARD
        })
    });
    static EVENTS = Object.freeze([EVENT.CLICK]);
    static WORDS = Object.freeze(["text"]);
    static BEHAVIOR = ItemBehavior;

    /** Its words, when it shows words. */
    get text() { return this.el.textContent; }
    set text(words) { if (words != null) this.el.textContent = String(words); }

    get selected() { return this._get("selected"); }
    set selected(on) { this._set("selected", !!on); }

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** Where the page's keys are, for what the row stands for: away, candidate, lent or held. */
    get keys() { return this._get("keys"); }
    set keys(at) { this._set("keys", at == null ? STATE.KEYBOARD.rest : String(at)); }

    setOnClick(handler) { this._setOn(EVENT.CLICK, handler); }
    setOnSelectedChanged(handler) { this._setOn(STATE.SELECTED.changed, handler); }
    setOnCurrentChanged(handler) { this._setOn(STATE.CURRENT.changed, handler); }
    setOnKeyboardChanged(handler) { this._setOn(STATE.KEYBOARD.changed, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const ListRowFactory = new SimpleComponentFactory(ListRow);
