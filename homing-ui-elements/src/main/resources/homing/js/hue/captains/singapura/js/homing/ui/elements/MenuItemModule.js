// =============================================================================
// MenuItem — one choice of a menu: its glyph, its name, its hint, and its way
// into a submenu. A simple component on a <div role="menuitem">, in the menu
// item's look, made on the branch its owner hands it with its parts the
// house's: its glyph an icon - its own word, or the check while it is checked,
// as its owner says - its hint a caption cast as a hint, and, when it opens
// rows, the disclose mark at its end. Usable or off (Enabled, aria-disabled);
// the one its menu's cursor is on (Pointed, data-highlighted) - the pointer
// coming onto it points at it, fired as its change; checked, or not, or
// neither (aria-checked); and, for one that opens rows, whether they are open
// (aria-expanded). A press while it is usable is its click. Where it sits, and
// whether it is shown, is its menu's.
//
//   var item = new MenuItem(branch.createBranch("row-rotate"), { label: "Rotate", icon: "rotate", hint: "a quarter turn", expanded: false? }, ROLE.CHOICE);
//     expanded   null (the rest): it opens nothing; false or true: it opens rows, shut or open
//   item.el / item.root  item.mark (its icon: set, clear)  item.label
//   item.enabled  item.pointed  item.checked  item.expanded
//   item.setOnClick(handler)  item.setOnEnabledChanged(handler)  item.setOnPointedChanged(handler)
// =============================================================================

const _menuItemOwner = Object.freeze({ toString: () => "menuItem" });

class MenuItem extends SimpleComponent {
    static LEAF = "menu-item";
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.setAttribute("aria-disabled", on ? "false" : "true"); },
            state: STATE.ENABLED
        }),
        pointed: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("data-highlighted", "true"); else el.removeAttribute("data-highlighted"); },
            state: STATE.POINTED
        }),
        checked: Object.freeze({
            values: Object.freeze([null, false, true]),
            rest: null,
            reflect: function (el, v) { if (v === null) el.removeAttribute("aria-checked"); else el.setAttribute("aria-checked", v ? "true" : "false"); }
        }),
        expanded: Object.freeze({
            values: Object.freeze([null, false, true]),
            rest: null,
            reflect: function (el, v) {
                if (v === null) { el.removeAttribute("aria-haspopup"); el.removeAttribute("aria-expanded"); return; }
                el.setAttribute("aria-haspopup", "menu");
                el.setAttribute("aria-expanded", v ? "true" : "false");
            }
        })
    });
    static EVENTS = Object.freeze([EVENT.CLICK]);
    static WORDS = Object.freeze(["label"]);
    static BEHAVIOR = ItemBehavior;

    constructor(branch, props, role) {
        if (!branch || typeof branch.elementMaker !== "function") throw new Error("[MenuItem] a branch of its own is required");
        var p = Object.assign({}, props || {}), icon = p.icon, hint = p.hint;
        delete p.icon;
        delete p.hint;
        branch.activate(_menuItemOwner);
        super(branch.elementMaker("item"), p, role);
        css.addClass(this.el, el_menu_item);
        this.el.setAttribute("role", "menuitem");
        this.el.setAttribute("tabindex", "-1");
        // its glyph, the house's icon: its own word, or none - at its width either way, so the names beside it align
        this.mark = new Icon(branch.createElement("mark", Icon.TAG), icon ? { name: icon } : null, ROLE.SYMBOL);
        this.el.appendChild(this.mark.el);
        this._label = branch.createElement("label", "span");
        css.addClass(this._label, el_menu_item_label);
        this.el.appendChild(this._label);
        if (hint) {   // its hint, the house's caption cast as a hint
            this._hint = new Caption(branch.elementMaker("hint"), { text: String(hint) }, ROLE.HINT);
            css.addClass(this._hint.el, el_menu_item_hint);
            this.el.appendChild(this._hint.el);
        }
        if (this._get("expanded") !== null) {   // its way into its rows: the disclose mark, at its end
            this._disclose = new Icon(branch.createElement("disclose", Icon.TAG), { name: "disclose" }, ROLE.DISCLOSE);
            css.addClass(this._disclose.el, el_menu_item_disclose);
            this.el.appendChild(this._disclose.el);
        }
        this.root = this.el;
        if (p.label != null) this.label = p.label;
    }

    /** Its name, as its label's words. */
    get label() { return this._label ? this._label.textContent : ""; }
    set label(words) { if (this._label) this._label.textContent = words == null ? "" : String(words); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    /** The one its menu's cursor is on. */
    get pointed() { return this._get("pointed"); }
    set pointed(on) { this._set("pointed", !!on); }

    /** Checked, not, or neither: null. */
    get checked() { return this._get("checked"); }
    set checked(v) { this._set("checked", v == null ? null : !!v); }

    /** For one that opens rows, whether they are open. */
    get expanded() { return this._get("expanded"); }
    set expanded(on) { if (this._get("expanded") !== null) this._set("expanded", !!on); }

    /** Pressed while usable: what it does, once and for good. */
    setOnClick(handler) { this._setOn(EVENT.CLICK, handler); }
    setOnEnabledChanged(handler) { this._setOn(STATE.ENABLED.changed, handler); }
    /** Pointed at - by the pointer coming onto it, the event the pointer's - or no longer. */
    setOnPointedChanged(handler) { this._setOn(STATE.POINTED.changed, handler); }
}
