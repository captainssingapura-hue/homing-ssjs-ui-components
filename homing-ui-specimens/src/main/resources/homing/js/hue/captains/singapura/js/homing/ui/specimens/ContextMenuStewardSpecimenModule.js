// =============================================================================
// ContextMenuStewardSpecimen — the house's keeper of context menus in action,
// starting from its fundamental invariant: ONE per page. Asked to keep this
// page's menus, it is refused when the page has its steward already - as a
// workspace does, which keeps the menus of its tabs and regions; right-press a
// tab of this workspace to see that one at work. On a page with none, it is
// the page's: two kinds, a file's and a folder's, one instance each, and one
// menu active - opening the folder's while the file's is open closes the
// file's first, and says so - the keys claimed while a menu is open, and
// given back on close; asked, it says what is open and for what.
//
//   new ContextMenuStewardSpecimen(branch, { leaf, say })   leaf: "context-menu-steward"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _contextMenuStewardSpecimen = Object.freeze({ toString: () => "contextMenuStewardSpecimen" });

class ContextMenuStewardSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_contextMenuStewardSpecimen);
        this.branch = branch;
        this._menus = null;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        this.root = root;
        try {
            this._menus = new ContextMenuSteward(branch.createBranch("menus"), {
                keyboard: KeyboardStewardInstance, keyboardId: "specimen-context-menu-steward",
                onEvent: function (ev) {
                    if (ev.kind === "Opened") say("the " + ev.menuKind + "'s menu opened - the one active");
                    else if (ev.kind === "Picked") say("picked on the " + ev.menuKind + ": " + ev.itemId);
                    else if (ev.kind === "Closed") say("the " + ev.menuKind + "'s menu closed: " + ev.reason);
                }
            });
        } catch (e) {
            var text = branch.createElement("refused", "p");
            css.addClass(text, sp_text);
            text.textContent = "This page has its steward already - the workspace's, which keeps the menus of its tabs and its regions. "
                + "Right-press a tab of this workspace, or the ground between its panes, to see that one at work.";
            root.appendChild(text);
            say("asked to keep this page's menus: refused - " + e.message);
            return;
        }
        this._menus.define("file").row("open", "Open").row("rename", "Rename", { hint: "F2" }).divider().row("delete", "Delete", { icon: "remove" }).done();
        this._menus.define("folder").row("open", "Open").row("new-file", "New file", { icon: "add" }).divider().row("delete", "Delete", { icon: "remove" }).done();
        this._menus.handle("file", { pick: function () {} });
        this._menus.handle("folder", { pick: function () {} });

        var row = branch.createElement("targets", "div");
        css.addClass(row, sp_row);
        ["file", "folder"].forEach(function (kind) {
            var box = branch.createElement("target-" + kind, "div");
            css.addClass(box, sp_host);
            var text = branch.createElement("target-" + kind + "-text", "p");
            css.addClass(text, sp_text);
            text.textContent = "A " + kind + ": right-press here.";
            box.appendChild(text);
            box.addEventListener("contextmenu", function (e) {
                if (self._menus.open(kind, { name: "the " + kind }, { x: e.clientX, y: e.clientY }, { anchor: box })) e.preventDefault();
            });
            row.appendChild(box);
        });
        root.appendChild(row);

        var ask = branch.createElement("ask", "div");
        css.addClass(ask, sp_row);
        var what = new ButtonBuilder().label("What is open?").plain().onClick(function () {
            var kind = self._menus.active();
            say(kind ? "the " + kind + "'s menu, for " + self._menus.bound().name : "nothing is open; it knows " + self._menus.kinds().join(" and "));
        });
        ask.appendChild(what.build(branch.createElement("what", what.tag)).el);
        root.appendChild(ask);
        say("the page's steward: right-press the file, then the folder - one menu is active at a time");
    }

    /** A steward varies along no axis. */
    extent(axis, v) {}

    /** It takes the keys itself while a menu is open; there is nothing to hand on. */
    key(ev) { return false; }

    dispose() {
        if (this._menus) { try { this._menus.dispose(); } catch (e) {} this._menus = null; }
        try { this.branch.dissolve(); } catch (e) {}
    }
}
