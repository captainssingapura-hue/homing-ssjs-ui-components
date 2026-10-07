// =============================================================================
// DialogSpecimen — the house's dialog in action: one matter put to the user
// above the page. Modal, it holds the page behind back - a scrim over it, and
// nothing there answers - until it is settled; not modal, the page still
// answers. It takes the keys while it is open: Escape closes it, Enter does
// the primary action, and they go back to whoever held them. Every opening,
// action and close is said.
//
//   new DialogSpecimen(branch, { leaf, say })   leaf: "dialog"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _dialogSpecimen = Object.freeze({ toString: () => "dialogSpecimen" });

class DialogSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_dialogSpecimen);
        this.branch = branch;
        this._say = say;
        this._dialog = null;
        this._opened = 0;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var modal = new ButtonBuilder().label("Open it, modal").onClick(function () { self._open(true); });
        row.appendChild(modal.build(branch.createElement("modal", modal.tag)).el);
        var loose = new ButtonBuilder().label("Open it, not modal").plain().onClick(function () { self._open(false); });
        row.appendChild(loose.build(branch.createElement("loose", loose.tag)).el);
        root.appendChild(row);
        this.root = root;
        say("open the dialog: settle it by an action, by Enter for the primary one, or by Escape");
    }

    _open(modal) {
        if (this._dialog) { this._say("one is open already: settle it first"); return; }
        var self = this, say = this._say, n = ++this._opened, how = "";
        this._dialog = new Dialog(this.branch.createBranch("dialog-" + n), {
            title: "Discard the draft?",
            modal: modal,
            content: function (b, body) {
                var text = b.createElement("draft-note", "p");
                css.addClass(text, sp_text);
                text.textContent = "The draft has changes nobody has saved. Discarding it cannot be undone.";
                body.appendChild(text);
                return {};
            },
            actions: [
                { id: "keep", label: "Keep it", onClick: function (d) { how = "Keep it: the draft stays"; d.close(); } },
                { id: "discard", label: "Discard", primary: true, onClick: function (d) { how = "Discard, the primary action"; d.close(); } }
            ],
            keyboard: KeyboardStewardInstance,
            keyboardId: "specimen-dialog-" + n,
            onClose: function () { self._dialog = null; say("closed" + (how ? " - " + how : " - by Escape or its cross") + "; the keys go back to who held them"); }
        });
        say(modal ? "open, modal: the page behind is held back until it is settled" : "open, not modal: the page behind still answers");
    }

    /** A dialog varies along no axis. */
    extent(axis, v) {}

    /** The dialog takes the keys itself while it is open; there is nothing to hand on. */
    key(ev) { return false; }

    dispose() {
        if (this._dialog) { try { this._dialog.close(); } catch (e) {} this._dialog = null; }
        try { this.branch.dissolve(); } catch (e) {}
    }
}
