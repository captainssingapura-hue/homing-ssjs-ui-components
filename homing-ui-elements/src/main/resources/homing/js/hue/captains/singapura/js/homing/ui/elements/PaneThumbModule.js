// =============================================================================
// PaneThumb — a pane, picked by a small picture of where it is, with its name: a
// thumbnail's, a grid's mirror's cell. A simple component on a <div>, in the pane
// thumbnail's look, made on the branch its owner hands it with its part the
// house's: its name, a kicker cast as its name. Where it is and how large is its
// owner's placement; whether it is the one its owner's cursor is at, its
// Current state, held as its class while it holds (aria-current written with it).
//
//   var t = new PaneThumb(branch.createBranch("cell-0"), { name: "Editor", current: true }, ROLE.CELL);
//   t.root  t.name  t.current
//   t.setOnCurrentChanged(handler)
// =============================================================================

const _paneThumbOwner = Object.freeze({ toString: () => "paneThumb" });

class PaneThumb extends SimpleComponent {
    static LEAF = "pane-thumb";
    static TAG = "div";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: STATE.CURRENT
        })
    });
    static WORDS = Object.freeze(["name"]);
    static BEHAVIOR = MarkBehavior;

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[PaneThumb] a branch of its own is required");
        branch.activate(_paneThumbOwner);
        super(branch.elementMaker("thumb"), props, role);
        this._name = new Kicker(branch.elementMaker("name"), {}, ROLE.NAME);   // its name, a kicker cast as its name
        css.addClass(this._name.el, el_pane_thumb_name);
        this.el.appendChild(this._name.el);
        this.root = this.el;
        if (props && props.name != null) this.name = props.name;
    }

    /** Its name: its kicker's words. */
    get name() { return this._name ? this._name.text : ""; }
    set name(words) { if (this._name) this._name.text = words == null ? "" : String(words); }

    /** The one its owner's cursor is at. */
    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** Made the one its owner's cursor is at, or no longer: by its owner. */
    setOnCurrentChanged(handler) { this._setOn(STATE.CURRENT.changed, handler); }
}
