// =============================================================================
// MultiTabPane — a pane of tabs: its bar over its slot, where what it shows is
// mounted, one at a time. Ported (RFC 0071) a step at a time: for now its bar
// with its one plus, and its slot, empty. It manages no tab in or out (owner,
// 22 Oct): its caller does. A press on its bar's plus is passed on - its own
// TabAddControl going up - and what is added, if anything, is its caller's to
// decide. Its chips, the tabs it captures and shows, and its keys come as they
// are ported.
//
//   var pane = new MultiTabPane(branch.createBranch("pane"),
//                  new FixedMultiTabPane(new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), null), me.place(FixedMultiTabPane));
//   pane.onChange(StateTabAddControl, listener)   its bar's plus pressed: the count up by one
//   pane.adds      how many adds were asked of it, from none
//   pane.keys      where the page's keys are on it - the steward's to say
//   pane.note      its note's words, where it was made with a note
//   pane.root      its element; pane.dispose()
// A composed component on the style manager (RFC 0071): it takes a branch of its
// own and makes its parts on it; its module stamped with its header (D19).
// =============================================================================

const _multiTabPaneOwner = Object.freeze({ toString: () => "multi-tab-pane" });

/** Its bar's plus pressed: the pane's own count of the adds asked of it, up by one. */
class MultiTabPaneAdd {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateTabAddControl; }
    handle() { this._pane._asked(); }
}

class MultiTabPane extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        keys: Object.freeze({ values: StateKeyboard.states, rest: StateKeyboard.rest, state: StateKeyboard }),
        adds: Object.freeze({ rest: 0, state: StateTabAddControl })   // its bar's adds, passed on: no class
    });
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[MultiTabPane] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_multiTabPaneOwner);
        super(branch.elementMaker("pane"), fixed, FixedMultiTabPane, place ?? manageMultiTabPane);
        this.branch = branch;
        css.addClass(this.el, mtp_pane);
        // its bar, the tab strip its fixed object holds: what it asks for passed on, a level up
        this._strip = new TabStrip(branch.createBranch("strip"), this.fixed.tabs, this._place(this.fixed.tabs));
        this._strip.onChange(StateTabAddControl, new MultiTabPaneAdd(this));
        this.el.appendChild(this._strip.el);
        // its slot: its own element, where its caller mounts what it shows - empty until then
        this._slot = branch.createElement("slot", "div");
        this._room(ROOM_SLOT, this._slot);
        css.addClass(this._slot, mtp_content);
        css.addClass(this._slot, mtp_empty);
        this.el.appendChild(this._slot);
        this._note = null;
        if (this.fixed.note !== null) {   // its note, the house's caption cast as a note: under its slot
            this._note = new Caption(branch.elementMaker("note"), this.fixed.note, this._place(this.fixed.note));
            this.el.appendChild(this._note.el);
        }
        this.root = this.el;
    }

    /** How many adds were asked of it, from none: its bar's plus pressed. */
    get adds() { return this._get("adds"); }

    /** Where the page's keys are on it: away, candidate, lent or held - the steward's to say. */
    get keys() { return this._get("keys"); }

    /** Its note's words, where it was made with a note. */
    get note() { return this._note ? this._note.text : ""; }
    set note(words) {
        if (!this._note) throw new TypeError("[MultiTabPane] made with no note: whether it has one is fixed for its life");
        this._note.text = words == null ? "" : String(words);
    }

    /** It and everything on its branch go together. */
    dispose() { this.branch.dissolve(); }

    /** Its bar's plus pressed, as its listener hears it: one more add asked. */
    _asked() { this._change("adds", this.adds + 1); }
}
