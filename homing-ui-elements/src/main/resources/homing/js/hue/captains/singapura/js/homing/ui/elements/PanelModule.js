// =============================================================================
// Panel — a named region that fills what holds it - a cell of a grid, a pane's
// tab, a page - and brings nothing else: its head, the panel's bar, naming it,
// what acts on it at the head's end; its body, a room of its own (RFC 0067,
// D57), what is mounted in it. A composed component on the style manager (RFC
// 0071): it takes a branch of its own and makes on it its <section>, its body,
// and its head when it is first named or handed something to act on it - the
// house's panel bar, cast as its head, placed along its slot by its own
// reference. It measures nothing: what holds it decides how big it is.
//
//   var panel = new Panel(branch.createBranch("orders"), new FixedPanel(), me.slot(SLOT_…));
//   panel.root               the <section>
//   panel.title = "Orders"   its name: its head's title, and its label; "" for none
//   panel.end(c)             c at its head's end - what acts on the panel: a component,
//                            by its element (el) or its tree (root)
//   panel.mount(c)           c in its body - what it shows: a component, by its element
//                            or its tree; what is mounted is the mounter's to dispose
//   panel.elevation          "flat", "elevated" or "sunken": how far off its plane the
//                            app sits it - what a register means is the app's
//   panel.current            whether it is the current one, said in colour alone
//   panel.setOnElevationChanged(handler) / setOnCurrentChanged(handler)
//   panel.dispose()          the panel and everything on its branch
//
// It takes no keys and claims nothing: a panel is furniture. What it was told of
// the keys inside it - it read data-keys, which nothing writes now (D16) - waits
// for a component's own events (the port's question 2), and a body whatever is
// mounted fills, with no air and no scroll, for a component's fixed fields
// (question 5). `css` is injected with the styles import.
// =============================================================================

const _panelOwner = Object.freeze({ toString: () => "panel" });

class Panel extends SimpleComponent {
    static TAG = "section";
    /** Its two states, each the app's to say: how far off its plane it sits, and whether it is the current one. */
    static AXES = Object.freeze({
        elevation: Object.freeze({
            values: Object.freeze(["flat", "elevated", "sunken"]),
            rest: "flat",
            state: StateElevation
        }),
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },   // said by its reflection, never false
            state: StateCurrent
        })
    });
    static BEHAVIOR = MarkBehavior;
    #branch;
    #head = null;
    #body;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[Panel] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_panelOwner);
        super(branch.elementMaker("panel"), fixed, FixedPanel, place ?? managePanel);
        this.#branch = branch;
        css.addClass(this.el, el_panel);
        this.#body = branch.elementMaker("body")("div");
        this._room(ROOM_BODY, this.#body);
        css.addClass(this.#body, el_panel_body);
        css.addClass(this.#body, el_panel_body_air);
        this.el.appendChild(this.#body);
        this.root = this.el;
    }

    /** Its name: its head's title, and its label; "" when it has none. */
    get title() { return this.#head === null ? "" : this.#head.title; }
    set title(words) {
        var w = words == null ? "" : String(words);
        if (w !== "") this.#bar().title = w;
        else if (this.#head !== null) this.#head.title = "";
        if (w === "") this.el.removeAttribute("aria-label"); else this.el.setAttribute("aria-label", w);
    }

    /** A component at its head's end - what acts on the panel, the caller's. */
    end(c) { this.#bar().end(c); return this; }

    /** A component in its body - what it shows, the caller's: by its element (el) or its tree (root). */
    mount(c) {
        var e = c && (c.root || c.el);
        if (!e) throw new Error("[Panel] mounts a component: one with its element, el, or its tree, root");
        this.#body.appendChild(e);
        return this;
    }

    get elevation() { return this._get("elevation"); }
    set elevation(e) { this._set("elevation", e == null ? "flat" : e); }

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** How far off its plane it sits, changed. */
    setOnElevationChanged(handler) { this._setOn(StateElevation, handler); }

    /** Made the current one, or no longer. */
    setOnCurrentChanged(handler) { this._setOn(StateCurrent, handler); }

    /** The panel and everything on its branch go together; what was mounted in its body is the mounter's to dispose. */
    dispose() {
        if (this.el.parentNode) this.el.parentNode.removeChild(this.el);
        try { this.#branch.dissolve(); } catch (e) {}
    }

    /** Its head, the panel's bar: made the first time it is named or handed something to act on it, before its body. */
    #bar() {
        if (this.#head === null) {
            this.#head = new PanelBar(this.#branch.createBranch("head"), new FixedPanelBar(), this._slot(SLOT_PANEL_BAR_HEAD));
            this.el.insertBefore(this.#head.root, this.#body);
        }
        return this.#head;
    }
}
