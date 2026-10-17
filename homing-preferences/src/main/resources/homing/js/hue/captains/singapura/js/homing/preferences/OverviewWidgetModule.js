// =============================================================================
// OverviewWidget — a group's page. new OverviewWidget(branch, params) → root, dispose
//   params: { label, summary?, settings: [{ name, label }] }
// Lists the settings under the group with the value each has in force, read
// from the steward and redrawn when it changes; one button forgets them all.
// =============================================================================

const _overviewOwner = Object.freeze({ toString: () => "overviewWidget" });

/** The reset pressed: every setting the overview shows goes back to the site's default. */

class OverviewReset {
    constructor(settings) { this._settings = settings; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle() { this._settings.forEach(function (s) { PreferenceStewardInstance.forget(s.name); }); }
}

class OverviewWidget {
    constructor(branch, params) {
        branch.activate(_overviewOwner);
        var self = this;
        var settings = (params && params.settings) || [];

        var root = branch.createElement("root", "div");
        // its texts are the house's, each cast as what it is to the overview: placed by it, looking as they are
        var kicker = new Kicker(KickerFactory.create(branch, "kicker"), { text: "group" }, ROLE.CATEGORY);
        css.addClass(kicker.el, pv_kicker);
        root.appendChild(kicker.el);
        var title = new Heading4(Heading4Factory.create(branch, "title"), { text: params.label || "" }, ROLE.TITLE);
        css.addClass(title.el, pv_title);
        root.appendChild(title.el);
        if (params.summary) {
            var summary = new Lede(LedeFactory.create(branch, "summary"), { text: params.summary }, ROLE.SUMMARY);
            css.addClass(summary.el, pv_summary);
            root.appendChild(summary.el);
        }

        var list = branch.createElement("settings", "div");
        css.addClass(list, pv_children);
        list.setAttribute("role", "list");
        this._values = [];
        settings.forEach(function (s, i) {
            // the line between two settings, the house's separator cast as a break: the list's own part, never a row's
            if (i > 0) list.appendChild(new Separator(branch.elementMaker("rule-" + i), {}, ROLE.BREAK).el);
            // each setting the preferences' setting row, as an entry of the list: its name and its value its own
            var row = new SettingRow(branch.createBranch("s-" + i), { label: s.label || s.name }, ROLE.ENTRY);
            list.appendChild(row.el);
            self._values.push({ name: s.name, row: row });
        });
        root.appendChild(list);

        var actions = branch.createElement("actions", "div");
        css.addClass(actions, pv_actions);
        this._reset = new PlainButton(PlainButtonFactory.create(branch, "reset"), { label: "Use the site's defaults for all of these" }, ROLE.RESET);
        this._reset.setOnClick(new OverviewReset(settings));
        actions.appendChild(this._reset.el);
        root.appendChild(actions);
        this.root = root;

        this._stop = PreferenceViewInstance.onChange(function () { self._draw(); });
        this._draw();
    }

    _draw() {
        var any = false;
        this._values.forEach(function (v) {
            var stored = PreferenceViewInstance.preferred(v.name), pinned = PreferenceViewInstance.override(v.name);
            v.row.value = pinned ? pinned + " (pinned)" : stored ? stored : "default";
            if (stored) any = true;
        });
        this._reset.enabled = any;
    }

    dispose() { this._stop(); }
}
