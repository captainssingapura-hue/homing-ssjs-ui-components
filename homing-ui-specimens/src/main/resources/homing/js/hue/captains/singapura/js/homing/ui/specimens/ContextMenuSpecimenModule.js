// =============================================================================
// ContextMenuSpecimen — the house's context menu in action, driven as its
// steward drives it: the choices that apply to a document, shown where the
// user asks - a right-press in the box, or the button, which shows it from the
// keyboard with its first row lit. The keys handed on walk it: the arrows move
// along the rows, Right and Enter open a row's rows beside it, Left steps back,
// Enter or a press picks; Escape hides it. Below, the same menu shown still,
// every level open. Every show, pick and hide is said.
//
// The page's one steward - one per page, which keeps the workspace's own menus
// - is not this specimen's to take, so the specimen holds the menu itself.
//
//   new ContextMenuSpecimen(branch, { leaf, say })   leaf: "context-menu"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _contextMenuSpecimen = Object.freeze({ toString: () => "contextMenuSpecimen" });

var _DOCUMENT_MENU = Object.freeze({ kind: "document", nodes: [
    { id: "open", label: "Open" },
    { id: "pin", label: "Pin it", icon: "pin" },
    { id: "rename", label: "Rename", hint: "F2" },
    { id: "share", label: "Share", section: 1, nodes: [{ id: "link", label: "Copy the link" }, { id: "mail", label: "Send it by mail" }] },
    { id: "delete", label: "Delete", icon: "remove", section: 2 }
] });

class ContextMenuSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_contextMenuSpecimen);
        this.branch = branch;
        this._say = say;
        this._shown = false;
        this._layer = branch.createElement("layer", "div");
        css.addClass(this._layer, sp_layer);
        this._menu = new ContextMenu(branch.createBranch("menu"), _DOCUMENT_MENU, { pick: function (id) { say("picked: " + id); self._hide(null); } });

        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var target = branch.createElement("target", "div");
        css.addClass(target, sp_host);
        var text = branch.createElement("target-text", "p");
        css.addClass(text, sp_text);
        text.textContent = "A document. Right-press anywhere in this box for what applies to it.";
        target.appendChild(text);
        target.addEventListener("contextmenu", function (e) { e.preventDefault(); self._show(e.clientX, e.clientY, false); });
        root.appendChild(target);

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var byKeys = new ButtonBuilder().label("Show it from the keyboard").plain().onClick(function () {
            var r = target.getBoundingClientRect();
            self._show(r.left + 24, r.top + 24, true);
        });
        row.appendChild(byKeys.build(branch.createElement("by-keys", byKeys.tag)).el);
        var hide = new ButtonBuilder().label("Hide it").plain().onClick(function () { self._hide("by call"); });
        row.appendChild(hide.build(branch.createElement("hide", hide.tag)).el);
        root.appendChild(row);

        var still = branch.createElement("still", "div");
        css.addClass(still, sp_frames);
        root.appendChild(still);
        this._still = new ContextMenu(branch.createBranch("still-menu"), _DOCUMENT_MENU, {}, { specimen: true });
        this._still.bind(null);
        this._still.mount(still);

        this.root = root;
        say("right-press the box, or show it from the keyboard: arrows, Right, Left, Enter, Escape");
    }

    _show(x, y, fromKeyboard) {
        if (this._shown) this._hide("replaced");
        this._menu.bind(this);
        document.body.appendChild(this._layer);
        var at = this._menu.show(this._layer, { x: x, y: y }, { w: window.innerWidth, h: window.innerHeight }, fromKeyboard);
        // as its steward's claim does: the frame does not keep the browser's focus, so the keys come through their holder
        if (document.activeElement && this._menu.el.contains(document.activeElement)) document.activeElement.blur();
        this._shown = true;
        this._say("shown at " + Math.round(at.x) + ", " + Math.round(at.y) + (fromKeyboard ? ", its first row lit" : ""));
    }

    _hide(reason) {
        if (!this._shown) { if (reason) this._say("nothing is shown"); return; }
        this._menu.hide();
        this._menu.unbind();
        if (this._layer.parentNode) this._layer.parentNode.removeChild(this._layer);
        this._shown = false;
        if (reason) this._say("hidden: " + reason);
    }

    /** A menu varies along no axis. */
    extent(axis, v) {}

    /** The keys its holder hands on, while it is shown: to the menu; Escape hides it. */
    key(ev) {
        if (!this._shown) return false;
        if (ev.key === "Escape") { this._hide("escape"); return true; }
        return this._menu.key(ev);
    }

    dispose() {
        this._hide(null);
        try { this._menu.dispose(); } catch (e) {}
        try { this._still.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
