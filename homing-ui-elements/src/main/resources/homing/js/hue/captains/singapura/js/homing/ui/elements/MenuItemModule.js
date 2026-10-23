// =============================================================================
// MenuItem — what the two kinds of a menu's item share (owner, 23 Oct): one
// choice of a menu, its name, its own icon leading it or none, its hint. Never
// made on its own: each kind is - PlainMenuItem, which does something or opens
// the rows it leads to, and CheckMenuItem, which is checked or not - each with
// its own fixed type, binding and rooms, handed in. What it is made with is fixed
// for its life. Its leading place is kept whether an icon is shown there or not,
// so the names in a menu line up - a room of its own, as its name is.
//
// Its states: usable, or off (Enabled); where its menu's cursor is (Pointed) -
// its menu's to say, and the pointer coming onto it points at it, while it is
// usable: an off row never takes the cursor and stays grey (owner, 23 Oct); and
// its picks (PickControl), its press while it is usable passed on, handled by
// none: what a pick does, its menu decides. It takes no focus: its menu's keys
// are its menu's.
//
//   class PlainMenuItem extends MenuItem { constructor(branch, fixed, place) {
//       super(branch, fixed, FixedPlainMenuItem, place ?? managePlainMenuItem, { lead: ROOM_LEAD, label: ROOM_LABEL }); } }
//   item.label   item.hint   item.enabled   item.pointed   item.picks
//   item.onChange(StatePickControl, listener)   picked: the count up by one
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _menuItemOwner = Object.freeze({ toString: () => "menu-item" });

class MenuItem extends SimpleComponent {
    static TAG = "div";
    /** What every kind has; each adds its own. */
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: StateEnabled
        }),
        pointed: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StatePointed }),
        picks: Object.freeze({ rest: 0, state: StatePickControl })   // its presses while usable, passed on: no class
    });
    static BEHAVIOR = ItemBehavior;

    constructor(branch, fixed, Fixed, place, rooms) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[MenuItem] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_menuItemOwner);
        super(branch.elementMaker("item"), fixed, Fixed, place);
        this.branch = branch;
        css.addClass(this.el, el_menu_item);
        this.el.setAttribute("role", "menuitem");
        this.el.setAttribute("tabindex", "-1");
        // its leading place: a room of its own, its own icon in it - and, for a kind that has one, its check
        this._lead = branch.createElement("lead", "span");
        this._room(rooms.lead, this._lead);
        css.addClass(this._lead, el_menu_item_lead);
        this.el.appendChild(this._lead);
        this._icon = null;
        if (this.fixed.leading !== null) {
            this._icon = new Icon(branch.elementMaker("icon"), this.fixed.leading, this._place(this.fixed.leading, RoleLeading));
            this._lead.appendChild(this._icon.el);
        }
        // its name: a room of its own
        this._label = branch.createElement("label", "span");
        this._room(rooms.label, this._label);
        css.addClass(this._label, el_menu_item_label);
        this.el.appendChild(this._label);
        // its hint, the house's caption cast as a hint
        this._hint = null;
        if (this.fixed.hint !== null) {
            this._hint = new Caption(branch.elementMaker("hint"), this.fixed.hint, this._place(this.fixed.hint));
            css.addClass(this._hint.el, el_menu_item_hint);
            this.el.appendChild(this._hint.el);
        }
        this.root = this.el;
    }

    /** Its name, as its label's words. */
    get label() { return this._label.textContent; }
    set label(words) { this._label.textContent = words == null ? "" : String(words); }

    /** Its hint's words, where it was made with a hint. */
    get hint() { return this._hint !== null ? this._hint.text : ""; }
    set hint(words) {
        if (this._hint === null) throw new TypeError("[MenuItem] made with no hint: whether it has one is fixed for its life");
        this._hint.text = words;
    }

    /** Usable, or off: off, it lets the cursor go. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) {
        this._set("enabled", !!on);
        if (!this.enabled && this.pointed) this._set("pointed", false);
    }

    /** The one its menu's cursor is on: never an off one. */
    get pointed() { return this._get("pointed"); }
    set pointed(on) {
        if (on && !this.enabled) throw new TypeError("[MenuItem] off: it takes no cursor");
        this._set("pointed", !!on);
    }

    /** How many times it was picked, from none: its presses while it was usable. */
    get picks() { return this._get("picks"); }

    /** It and everything on its branch go together. */
    dispose() { this.branch.dissolve(); }

    /** Pressed while usable, as its behaviour hears it: one more pick - and nothing else. */
    _clicked() { this._change("picks", this.picks + 1); }
}
