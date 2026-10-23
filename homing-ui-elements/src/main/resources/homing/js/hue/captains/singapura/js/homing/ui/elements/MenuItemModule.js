// =============================================================================
// MenuItem — one choice of a menu: its name, its own icon leading it, its hint,
// and its way into rows of its own. Ported (RFC 0071, 23 Oct): a composed
// component, made by its menu - cast as its choice - or on its own at the root.
// What it is made with is fixed for its life, in its fixed object: its own icon,
// or none; a check, for a row that can be checked - shown in its leading place,
// in place of its own icon, while it is checked (owner, 23 Oct: option a); its
// hint, the house's caption; its way on, the disclose icon, for a row that opens
// rows. Its leading place is kept whether an icon is shown there or not, so the
// names in a menu line up - a room of its own, as its name is.
//
// Its states: usable, or off (Enabled); where its menu's cursor is (Pointed) -
// its menu's to say, and the pointer coming onto it points at it; checked, for
// one that can be (Checked); its rows open, for one that opens rows (Expanded);
// and its picks (PickControl), its press while it is usable passed on, handled
// by none: what a pick does, its menu decides. It takes no focus: its menu's
// keys are its menu's.
//
//   var item = new MenuItem(branch.createBranch("rotate"), new FixedMenuItem(new FixedRotateIcon(), null, new FixedCaption(), null), me.place(FixedMenuItem));
//   item.label = "Rotate"   item.hint = "a quarter turn"
//   item.onChange(StatePickControl, listener)   picked: the count up by one
//   item.onChange(StatePointed, listener)       the cursor on it, or the pointer onto it
//   item.enabled  item.pointed  item.checked  item.expanded  item.picks
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _menuItemOwner = Object.freeze({ toString: () => "menu-item" });

class MenuItem extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: StateEnabled
        }),
        pointed: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StatePointed }),
        checked: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateChecked }),
        expanded: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateExpanded }),
        picks: Object.freeze({ rest: 0, state: StatePickControl })   // its presses while usable, passed on: no class
    });
    static BEHAVIOR = ItemBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[MenuItem] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_menuItemOwner);
        super(branch.elementMaker("item"), fixed, FixedMenuItem, place ?? manageMenuItem);
        this.branch = branch;
        css.addClass(this.el, el_menu_item);
        this.el.setAttribute("role", "menuitem");
        this.el.setAttribute("tabindex", "-1");
        // its leading place: a room of its own, its own icon in it, and the check beside it, the one shown at a time
        this._lead = branch.createElement("lead", "span");
        this._room(ROOM_LEAD, this._lead);
        css.addClass(this._lead, el_menu_item_lead);
        this.el.appendChild(this._lead);
        this._icon = null;
        if (this.fixed.leading !== null) {
            this._icon = new Icon(branch.elementMaker("icon"), this.fixed.leading, this._place(this.fixed.leading, RoleLeading));
            this._lead.appendChild(this._icon.el);
        }
        this._check = null;
        if (this.fixed.chosen !== null) {
            this._check = new Icon(branch.elementMaker("check"), this.fixed.chosen, this._place(this.fixed.chosen, RoleChosen));
            css.addClass(this._check.el, el_menu_item_off);   // shown while it is checked
            this._lead.appendChild(this._check.el);
            this.el.setAttribute("aria-checked", "false");
        }
        // its name: a room of its own
        this._label = branch.createElement("label", "span");
        this._room(ROOM_LABEL, this._label);
        css.addClass(this._label, el_menu_item_label);
        this.el.appendChild(this._label);
        // its hint, the house's caption cast as a hint
        this._hint = null;
        if (this.fixed.hint !== null) {
            this._hint = new Caption(branch.elementMaker("hint"), this.fixed.hint, this._place(this.fixed.hint));
            css.addClass(this._hint.el, el_menu_item_hint);
            this.el.appendChild(this._hint.el);
        }
        // its way into its rows, the disclose icon, at its end
        this._disclose = null;
        if (this.fixed.disclose !== null) {
            this._disclose = new Icon(branch.elementMaker("disclose"), this.fixed.disclose, this._place(this.fixed.disclose, RoleDisclose));
            css.addClass(this._disclose.el, el_menu_item_disclose);
            this.el.appendChild(this._disclose.el);
            this.el.setAttribute("aria-haspopup", "menu");
            this.el.setAttribute("aria-expanded", "false");
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

    /** Usable, or off. */
    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** The one its menu's cursor is on. */
    get pointed() { return this._get("pointed"); }
    set pointed(on) { this._set("pointed", !!on); }

    /** Checked: its check shown in its leading place, its own icon not. Only a row made with a check can be. */
    get checked() { return this._get("checked"); }
    set checked(on) {
        if (this._check === null) throw new TypeError("[MenuItem] made with no check: it cannot be checked");
        this._set("checked", !!on);
        this.el.setAttribute("aria-checked", this.checked ? "true" : "false");
        css.toggleClass(this._check.el, el_menu_item_off, !this.checked);
        if (this._icon !== null) css.toggleClass(this._icon.el, el_menu_item_off, this.checked);
    }

    /** Its rows open. Only a row made with its way on opens rows. */
    get expanded() { return this._get("expanded"); }
    set expanded(on) {
        if (this._disclose === null) throw new TypeError("[MenuItem] made with no way on: it opens no rows");
        this._set("expanded", !!on);
        this.el.setAttribute("aria-expanded", this.expanded ? "true" : "false");
    }

    /** How many times it was picked, from none: its presses while it was usable. */
    get picks() { return this._get("picks"); }

    /** It and everything on its branch go together. */
    dispose() { this.branch.dissolve(); }

    /** Pressed while usable, as its behaviour hears it: one more pick - and nothing else. */
    _clicked() { this._change("picks", this.picks + 1); }
}
