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
//
// A chip carries its parts as handles, _icon, _label, _mark and _close, and
// _chip, so a strip tells a chip from its own ground. A press on it, not on
// its cross, takes the native focus away from whatever had it, then selects:
// the chip takes no native focus itself. Pinned tabs and tabs that cannot be
// closed have no cross. `css` is injected with the styles import.
// =============================================================================

class TabChip {
    static mint(branch, tab, handlers, suffix) {
        var s = suffix == null ? "" : String(suffix);
        var c = branch.createElement("chip" + s, "div");
        c._chip = true;
        css.addClass(c, mtp_chip, mtp_chip_seated);
        c.setAttribute("role", "tab");
        c.setAttribute("aria-selected", "false");
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
        var mark = branch.createElement("mark" + s, "span");   // the within mark, after the label: shown only while the keys are in this tab
        css.addClass(mark, mtp_chip_mark);
        mark.setAttribute("aria-hidden", "true");
        c._mark = mark;
        c.appendChild(mark);

        var closeBtn = null;
        if (tab.closable !== false && !tab.pinned) {
            closeBtn = branch.createElement("close" + s, "button");
            closeBtn.type = "button";
            css.addClass(closeBtn, mtp_chip_close);   // the cross is the design's, drawn by the class's word
            closeBtn.setAttribute("aria-label", "Close " + label.textContent);
            closeBtn.setAttribute("tabindex", "-1");
            closeBtn.addEventListener("click", function (ev) { ev.stopPropagation(); handlers.onClose(); });
            c.appendChild(closeBtn);
        }
        c._close = closeBtn;
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
        if (chip._close) chip._close.setAttribute("aria-label", "Close " + t);
    }

    /** The chip's icon, now: the holder's element, or none. */
    static reicon(chip, icon) {
        if (chip._icon) TabChip._iconIn(chip._icon, icon);
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
