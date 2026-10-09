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
        var title = new Heading(HeadingFactory.create(branch, "title"), { text: params.label || "", level: 2 }, ROLE.TITLE);
        css.addClass(title.el, pv_title);
        root.appendChild(title.el);
        if (params.summary) {
            var summary = new Lede(LedeFactory.create(branch, "summary"), { text: params.summary }, ROLE.SUMMARY);
            css.addClass(summary.el, pv_summary);
            root.appendChild(summary.el);
        }

        var list = branch.createElement("settings", "ul");
        css.addClass(list, pv_children);
        this._values = [];
        settings.forEach(function (s, i) {
            var li = branch.createElement("s-" + i, "li");
            css.addClass(li, pv_child);
            var label = branch.createElement("s-" + i + "-label", "span");
            label.textContent = s.label || s.name;
            var value = new Caption(CaptionFactory.create(branch, "s-" + i + "-value"), {}, ROLE.VALUE);
            css.addClass(value.el, pv_option_note);
            li.appendChild(label);
            li.appendChild(value.el);
            list.appendChild(li);
            self._values.push({ name: s.name, caption: value });
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
            v.caption.text = pinned ? pinned + " (pinned)" : stored ? stored : "default";
            if (stored) any = true;
        });
        this._reset.enabled = any;
    }

    dispose() { this._stop(); }
}
