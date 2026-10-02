// =============================================================================
// TabPicker — a transient pane its host owns (RFC 0066 E3, the workspace
// detour: requests): the kinds that can be opened, a button each, and Cancel.
// Picked, it ASKS — onPick(kindId) — and opens nothing itself: whoever asked
// the host for a picker turns the pick into a request, and the host lets its
// picker go. Nothing of a register's: no tab, no widget, nothing logged. The
// multi-tab pane shows one in place of the tab it shows (MultiTabPane.pick).
//
//   new TabPicker(branch, { kinds, onPick, onCancel, prompt? })
//     branch  its own, unactivated; its host dissolves it when the picker goes
//     kinds   [{ id, label }] — what can be opened, in the order offered
//   picker.root
//   picker.focus()   the first choice takes the browser's focus, so the keys work at once
// =============================================================================

const _pickerOwner = Object.freeze({ toString: () => "tabPicker" });

class TabPicker {
    constructor(branch, opts) {
        var o = opts || {};
        if (!branch) throw new Error("[TabPicker] a branch of its own is required");
        if (typeof o.onPick !== "function" || typeof o.onCancel !== "function") throw new Error("[TabPicker] opts.onPick and opts.onCancel are required: it asks, and acts on nothing");
        branch.activate(_pickerOwner);
        var root = branch.createElement("picker", "div");
        css.addClass(root, mtp_opener);
        var note = branch.createElement("note", "p");
        css.addClass(note, mtp_opener_note);
        note.textContent = o.prompt == null ? "What should open here?" : String(o.prompt);
        root.appendChild(note);
        var grid = branch.createElement("grid", "div");
        css.addClass(grid, mtp_opener_grid);
        root.appendChild(grid);
        var first = null;
        (o.kinds || []).forEach(function (k, i) {
            var pick = TabPicker._button(branch, "pick-" + i, k.label == null ? k.id : k.label, function () { o.onPick(k.id); });
            grid.appendChild(pick);
            if (!first) first = pick;
        });
        var cancel = TabPicker._button(branch, "cancel", "Cancel", function () { o.onCancel(); });
        root.appendChild(cancel);
        this.root = root;
        this._first = first || cancel;
    }

    focus() { try { this._first.focus(); } catch (e) {} }

    static _button(branch, name, text, onClick) {
        var b = branch.createElement(name, "button");
        css.addClass(b, mtp_opener_pick);
        b.setAttribute("type", "button");
        b.textContent = String(text);
        b.addEventListener("click", onClick);
        return b;
    }
}
