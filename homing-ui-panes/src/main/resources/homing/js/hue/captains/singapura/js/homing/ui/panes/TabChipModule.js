// =============================================================================
// TabChip — the chip that names a tab on a strip: the icon's slot, the label,
// the within mark and the close cross, minted on the branch it is handed and
// wired to the handlers it is given. Static, as PaneTabs is. The strip mints
// its chips through it, and so does a tab-pane, which mints its one chip once
// and carries it from strip to strip: a chip is the same thing, the same
// elements and the same look, whoever holds it.
//
//   TabChip.mint(branch, { id, title, icon?, pinned, closable }, { onSelect, onClose, onMenu? }, suffix?) → the chip
//       suffix: what its elements' names end in, "" unless said. A branch of
//       the chip's own needs nothing more; a branch several chips share gives
//       each chip a suffix of its own.
//       onMenu(at, keyboard) → boolean, true when a menu was opened; only then
//       is the browser's own menu suppressed.
//   TabChip.retitle(chip, title)   the label, the tooltip and what the cross says it closes
//   TabChip.reicon(chip, icon?)    the holder's element in the icon's slot, or none
//   TabChip.keys(chip, state)      where the keys are, said on the chip: "held" while the
//       cursor is on its strip - lifted, its mark the colour part of the way -
//       "lent" while what its tab holds has them - down, the mark at full - or null
//   TabChip.grip(chip, on)         in the hand, or set down: dragging, and over the row
//
// A chip carries its parts as handles, _icon, _label, _mark and _close (the
// cross's element; _closer, the close button itself), and _chip, so a strip
// tells a chip from its own ground. A press on it, not on its cross, takes
// the native focus away from whatever had it, then selects: the chip takes no
// native focus itself. Pinned tabs and tabs that cannot be
// closed have no cross.
//
// Its look is the house's: a chip is the tab's leaf, wearing the tab's casting -
// the chip a tab-pane carries, a role that only places it - and the design draws
// it at rest, under the hand, selected and dragging, each state held as its class
// (TabChip.select, TabChip.grip; aria-selected and data-dragging written with
// them): the owner of its morphism, its cross flat. Its mark is the house's Icon,
// the within word, wearing the casting of an icon that says where the keys are.
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

/** A chip's mark's leaf: an icon, cast as the one that says where the keys are - the Icon component wears no look of its own yet. */
const _KEYS_ICON = Object.freeze({ LEAF: "icon" });

/** A chip's cross pressed: the press kept to the cross, and the chip's holder asked to close its tab. */
class TabChipClose {
    constructor(handlers) { this._handlers = handlers; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle(event) { if (event.raw) event.raw.stopPropagation(); this._handlers.onClose(); }
}

class TabChip {
    /** The tab's leaf: a chip is a tab, cast as the chip a tab-pane carries. */
    static LEAF = "tab";
    /** Its states, held as their classes while they hold: the one in use, and in the hand. */
    static AXES = Object.freeze({
        selected: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { el.setAttribute("aria-selected", on ? "true" : "false"); },
            state: STATE.SELECTED
        }),
        dragging: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("data-dragging", ""); else el.removeAttribute("data-dragging"); },
            state: STATE.DRAGGING
        })
    });

    /** How much of the colour a chip's mark carries while the cursor is on the bar: part of the way, towards what the tab in use carries at full. */
    static ON_THE_BAR = 0.45;

    static mint(branch, tab, handlers, suffix) {
        var s = suffix == null ? "" : String(suffix);
        var c = branch.createElement("chip" + s, "div");
        c._chip = true;
        css.addClass(c, mtp_chip);
        TabChip._looks().tab.wear(c);
        c.setAttribute("role", "tab");
        c._state = { selected: false, dragging: false };
        TabChip._looks().tab.show(c, c._state);
        c.title = tab.title == null ? "" : String(tab.title);

        // The icon's slot, always there and shown only when there is an icon:
        // a name on a branch is taken until the branch goes, so a slot made
        // and unmade with its icon could not be made twice.
        var slot = branch.createElement("icon" + s, "span");
        css.addClass(slot, mtp_chip_icon);
        slot.setAttribute("aria-hidden", "true");
        c._icon = slot;
        c.appendChild(slot);
        TabChip._iconIn(slot, tab.icon);

        var label = branch.createElement("label" + s, "span");
        css.addClass(label, mtp_chip_label);
        label.textContent = tab.title == null ? tab.id : String(tab.title);
        c._label = label;
        c.appendChild(label);
        // the within mark, after the label: shown only while the keys are in this tab - the house's icon, its word the
        // lexicon's as the style worn writes it, cast as the icon that says where the keys are
        var mark = new Icon(branch.createElement("mark" + s, Icon.TAG), { name: "within" }).el;
        css.addClass(mark, mtp_chip_mark);
        TabChip._looks().keys.wear(mark);
        c._mark = mark;
        c.appendChild(mark);

        var closer = null, closeBtn = null;
        if (tab.closable !== false && !tab.pinned) {
            // the house's close button, cast as what closes its owner: its cross and its frame are the style's
            closer = new CloseButton(branch.elementMaker("close" + s), { label: "Close " + label.textContent }, ROLE.CLOSE);
            closer.setOnClick(new TabChipClose(handlers));
            closeBtn = closer.el;
            css.addClass(closeBtn, mtp_chip_close);   // its place at the chip's end; its look is its own
            closeBtn.setAttribute("tabindex", "-1");
            c.appendChild(closeBtn);
        }
        c._close = closeBtn;
        c._closer = closer;   // the button itself: its name, and the tooltip that says it, are its own to change
        c.addEventListener("pointerdown", function (ev) {
            if (ev.button !== 0 || (closeBtn && closeBtn.contains(ev.target))) return;
            TabChip._letGo();
            handlers.onSelect();
        });
        if (handlers.onMenu) {
            c.addEventListener("contextmenu", function (ev) {
                if (handlers.onMenu({ x: ev.clientX, y: ev.clientY }, false)) ev.preventDefault();
            });
        }
        return c;
    }

    /** The chip's name, now: its label, its tooltip, and what its cross says it closes. */
    static retitle(chip, title) {
        var t = title == null ? "" : String(title);
        chip.title = t;
        if (chip._label) chip._label.textContent = t;
        if (chip._closer) chip._closer.label = "Close " + t;
    }

    /** The chip's icon, now: the holder's element, or none. */
    static reicon(chip, icon) {
        if (chip._icon) TabChip._iconIn(chip._icon, icon);
    }

    /**
     * Where the keys are, said on the chip. The whole operation is lift and
     * shift: "held" - the cursor on its strip, the arrows walking the tabs - the
     * chip is lifted off the row and its mark carries the colour part of the
     * way; "lent" - the keys inside its tab - it sits back down and the same
     * mark carries the same colour at full; anything else, neither.
     */
    static keys(chip, state) {
        var on = state === "held" || state === "lent";
        css.toggleClass(chip, mtp_chip_lifted, state === "held");   // on the bar: picked up. In the tab, or nowhere: down
        if (!chip._mark) return;
        css.toggleClass(chip._mark, mtp_chip_mark_on, on);
        css.extent(chip._mark, on ? (state === "held" ? TabChip.ON_THE_BAR : 1) : null);
    }

    /** The chip in use, or not: its Selected state, held as its class. */
    static select(chip, on) { TabChip._hold(chip, "selected", !!on); }

    /** One of its states, now: written, and its class held while it holds. */
    static _hold(chip, axis, value) {
        chip._state = Object.assign({}, chip._state || { selected: false, dragging: false });
        chip._state[axis] = value;
        TabChip._looks().tab.show(chip, chip._state);
    }

    /** The chip in the hand, or set down: the design's word for a thing dragged while it is, and lifted over the row along it. */
    static grip(chip, on) {
        TabChip._hold(chip, "dragging", !!on);
        css.toggleClass(chip, mtp_chip_dragging, !!on);
    }

    /** The tab's look and its mark's, made with the first chip: the page's castings are read by then. */
    static _looks() {
        if (!TabChip._worn) TabChip._worn = { tab: new Look(TabChip, ROLE.CHIP), keys: new Look(_KEYS_ICON, ROLE.KEYS) };
        return TabChip._worn;
    }

    /** What the slot shows: the one icon given, or nothing and hidden. */
    static _iconIn(slot, icon) {
        while (slot.firstChild) slot.removeChild(slot.firstChild);
        if (icon) slot.appendChild(icon);
        css.toggleClass(slot, mtp_chip_icon_on, !!icon);
    }

    /** The native focus taken away from whatever has it: a press on a chip is its holder's, and the keys after it are too. */
    static _letGo() {
        if (typeof document === "undefined") return;
        var a = document.activeElement;
        if (a && a !== document.body && typeof a.blur === "function") a.blur();
    }
}
