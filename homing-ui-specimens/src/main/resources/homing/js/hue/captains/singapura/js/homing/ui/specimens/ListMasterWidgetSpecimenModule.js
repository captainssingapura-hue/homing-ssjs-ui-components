// =============================================================================
// ListMasterWidgetSpecimen — the house's list master in action: a site's
// settings, listed by where they belong, depth said as indentation, so the
// user picks one to see beside the list. A press chooses a row; the keys
// handed on - the arrows, Home and End - move the row in force, and choosing
// is selecting. Each choice is said, with where it sits.
//
//   new ListMasterWidgetSpecimen(branch, { leaf, say })   leaf: "list-master-widget"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _listMasterSpecimen = Object.freeze({ toString: () => "listMasterSpecimen" });

var _SETTINGS = Object.freeze({ segment: "settings", children: [
    { segment: "appearance", children: [{ segment: "theme" }, { segment: "size" }] },
    { segment: "editor", children: [{ segment: "font" }, { segment: "wrap" }] },
    { segment: "language" }
] });

var _SETTING_LABELS = Object.freeze({
    "settings": "All settings", "settings/appearance": "Appearance", "settings/appearance/theme": "Theme",
    "settings/appearance/size": "Size", "settings/editor": "Editor", "settings/editor/font": "Font",
    "settings/editor/wrap": "Wrap lines", "settings/language": "Language"
});

class ListMasterWidgetSpecimen {
    constructor(branch, params) {
        var p = params || {};
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_listMasterSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        this._list = new ListMasterWidget(branch.createBranch("list"), { tree: _SETTINGS, labels: _SETTING_LABELS });
        this._list.onSelect(function (path) { say((_SETTING_LABELS[path] || path) + " chosen - " + path); });
        this._list.setActive(true);
        root.appendChild(this._list.root);
        this.root = root;
        say("press a setting, or press the list and move with the arrows, Home and End");
    }

    /** The list varies along no axis. */
    extent(axis, v) {}

    /** The keys its holder hands on: the arrows, Home and End move the row in force. */
    key(ev) { return this._list.key(ev); }

    dispose() {
        try { this._list.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
