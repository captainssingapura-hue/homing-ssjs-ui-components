// =============================================================================
// PanelBar — heads a panel: its title, the house's kicker cast as its title,
// and at its end what acts on the panel - the caller's, handed in. A composed
// component on the style manager (RFC 0071): it takes a branch of its own and
// makes on it its <header>, its title when it has words - placed along its slot
// by its own reference - and its end, a room of its own (RFC 0067): no part,
// its class the bar's, what sits in it the caller's.
//
//   var bar = new PanelBar(branch.createBranch("head"), new FixedPanelBar(), me.slot(SLOT_…));
//   bar.root          the bar, a <header>
//   bar.title = "…"   its title's words; "" or null for none - the kicker made
//                     when it has words, taken off when it has none
//   bar.end(c)        c at its end: a component, by its element (el) or its tree (root)
//
// The bar's classes only place. `css` is injected with the styles import.
// =============================================================================

const _panelBarOwner = Object.freeze({ toString: () => "panelBar" });

class PanelBar extends SimpleComponent {
    static TAG = "header";
    static BEHAVIOR = MarkBehavior;
    #branch;
    #title = null;
    #end;
    #made = 0;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[PanelBar] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_panelBarOwner);
        super(branch.elementMaker("bar"), fixed, FixedPanelBar, place ?? managePanelBar);
        this.#branch = branch;
        css.addClass(this.el, el_panel_head);
        this.#end = branch.elementMaker("end")("div");
        this._room(ROOM_END, this.#end);
        css.addClass(this.#end, el_panel_slot);
        this.el.appendChild(this.#end);
        this.root = this.el;
    }

    /** Its title's words; "" when it has none. */
    get title() { return this.#title === null ? "" : this.#title.text; }
    set title(words) {
        var w = words == null ? "" : String(words);
        if (w === "") {
            if (this.#title !== null) { this.#title.el.remove(); this.#title = null; }
            return;
        }
        if (this.#title === null) {
            this.#title = new Kicker(this.#branch.elementMaker("title-" + (++this.#made)), new FixedKicker(), this._slot(SLOT_KICKER_TITLE));
            css.addClass(this.#title.el, el_panel_title);
            this.el.insertBefore(this.#title.el, this.#end);
        }
        this.#title.text = w;
    }

    /** A component at its end - what acts on the panel, the caller's: by its element (el) or its tree (root). */
    end(c) {
        var e = c && (c.root || c.el);
        if (!e) throw new Error("[PanelBar] holds at its end a component: one with its element, el, or its tree, root");
        this.#end.appendChild(e);
        return this;
    }
}
