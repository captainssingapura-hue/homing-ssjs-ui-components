// =============================================================================
// PaneThumb — a pane, picked by a small picture of where it is, with its name: a
// thumbnail's, a grid's mirror's cell. A composed component on the style
// manager (RFC 0071): it takes a branch of its own and makes on it its element
// and its part, the house's - its name, a kicker cast as its name, placed along
// its slot by the placement its own reference gives. Where it is and how large
// is its owner's placement; whether it is the one its owner's cursor is at, its
// Current state, set through its reference (aria-current written with it).
//
//   var t = new PaneThumb(branch.createBranch("cell-0"), new FixedPaneThumb(), me.slot(SLOT_…));
//   t.name = "Editor"   t.current = true   t.root
//   t.setOnCurrentChanged(handler)
// =============================================================================

const _paneThumbOwner = Object.freeze({ toString: () => "paneThumb" });

class PaneThumb extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: StateCurrent
        })
    });
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[PaneThumb] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_paneThumbOwner);
        super(branch.elementMaker("thumb"), fixed, FixedPaneThumb, place ?? managePaneThumb);
        this._name = new Kicker(branch.elementMaker("name"), new FixedKicker(), this._slot(SLOT_KICKER_NAME));   // its name, a kicker cast as its name
        css.addClass(this._name.el, el_pane_thumb_name);
        this.el.appendChild(this._name.el);
        this.root = this.el;
    }

    /** Its name: its kicker's words. */
    get name() { return this._name.text; }
    set name(words) { this._name.text = words == null ? "" : String(words); }

    /** The one its owner's cursor is at. */
    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** Made the one its owner's cursor is at, or no longer: by its owner. */
    setOnCurrentChanged(handler) { this._setOn(StateCurrent, handler); }
}
