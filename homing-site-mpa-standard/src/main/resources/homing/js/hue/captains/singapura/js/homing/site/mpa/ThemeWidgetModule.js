// =============================================================================
// ThemeWidget — the design a site wears, as two preferences: its STYLE and its
// PALETTE, each its own (RFC 0067, appendix Two Classes per Leaf: a style's
// sheet and a palette's sheet, each switched on its own).
//   new ThemeWidget(branch, params) → root, dispose
//   params: { label, summary? }
//
// From /themes, the site's offer: its designs - each a style, by its row's slug,
// which is the style's token - and the palettes each is offered in: its own, or
// one that fits it. Two fields, the style's then the palette's, each with its
// own note and reset; a pick is the field's set - the steward's remember - and
// the page's sheets follow the store, the style's and the palette's apart.
//
// A style picked keeps the palette worn when that palette is offered for it,
// else takes its own. Nothing chosen, each is the site's default: the default
// theme's style and palette. The widget follows the store, as its fields do.
// =============================================================================

const _owner = Object.freeze({ toString: () => "themeWidget" });

/** A style or a palette pressed: its field set to its value - a style's own palette with it, when the worn one is not offered for it. */
class ThemePicked {
    constructor(widget, kind, value) { this._widget = widget; this._kind = kind; this._value = value; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle() { this._widget._pick(this._kind, this._value); }
}

class ThemeWidget {
    constructor(branch, params) {
        branch.activate(_owner);
        var self = this, p = params || {};
        this._branch = branch;
        this._reg = null;
        this._seq = 0;
        this._listsName = null;
        this._style = new PreferenceField(branch.createBranch("style"), { name: "style", kicker: "style", label: p.label || "Style", summary: p.summary });
        this._palette = new PreferenceField(branch.createBranch("palette"), { name: "palette", kicker: "palette", label: "Colours" });
        this._style.root.appendChild(this._palette.root);   // the palette's field after the style's, its own note and reset with it
        this.root = this._style.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._draw(); });

        // what it is waiting for, in the house's kicker, cast as its status
        var pending = new Kicker(KickerFactory.create(branch, "pending"), { text: "Loading the site's themes…" }, ROLE.STATUS);
        css.addClass(pending.el, pv_kicker);
        this._style.hold(pending.el);

        fetch("/themes").then(function (r) { if (!r.ok) throw new Error("/themes HTTP " + r.status); return r.json(); })
            .then(function (j) { self._reg = j; pending.el.remove(); self._draw(); })
            .catch(function (e) { pending.text = "The site's themes could not be loaded: " + e.message; });
    }

    /** The site's default, as a style and a palette: the default theme's row, and the palette it is worn in. */
    _defaults() {
        var reg = this._reg;
        for (var i = 0; i < reg.themes.length; i++) {
            var t = reg.themes[i];
            for (var j = 0; j < t.colours.length; j++) if (t.colours[j].slug === reg.default) return { style: t.slug, palette: t.colours[j].palette };
        }
        var first = reg.themes[0];
        return { style: first.slug, palette: ThemeWidget._own(first) };
    }
    _styleRow(token) {
        var reg = this._reg;
        for (var i = 0; i < reg.themes.length; i++) if (reg.themes[i].slug === token) return reg.themes[i];
        return null;
    }
    _paletteOf(token) {
        var reg = this._reg;
        for (var i = 0; i < reg.palettes.length; i++) if (reg.palettes[i].slug === token) return reg.palettes[i];
        return { label: token, inspiration: "" };
    }
    /** The palettes a style is offered in: its own, and those that fit it. */
    static _offered(row) { return row.colours.filter(function (c) { return c.own || c.fits; }); }
    static _own(row) { var own = row.colours.filter(function (c) { return c.own; })[0]; return own ? own.palette : null; }

    /** What is worn: each field's value, else the site's default. */
    _worn() {
        var d = this._defaults();
        return { style: this._style.value() || d.style, palette: this._palette.value() || d.palette };
    }

    _pick(kind, value) {
        if (kind === "palette") { this._palette.set(value); return; }
        var row = this._styleRow(value), worn = this._worn();
        var kept = row && ThemeWidget._offered(row).some(function (c) { return c.palette === worn.palette; });
        if (row && !kept) this._palette.set(ThemeWidget._own(row));   // the worn palette not offered for it: its own
        this._style.set(value);
    }

    // Every draw is a fresh sub-branch: the lists are rebuilt whole, and the
    // names on the previous branch go with it.
    _draw() {
        var reg = this._reg, self = this;
        if (!reg) return;
        var worn = this._worn();
        var row = this._styleRow(worn.style) || reg.themes[0];
        if (this._listsName) this._branch.dissolveBranch(this._listsName);
        this._listsName = "lists" + (++this._seq);
        var lists = this._branch.createBranch(this._listsName);
        lists.activate(_owner);
        this._style.hold(this._list(lists, "styles", "Styles", ROLE.STYLE, "style", reg.themes.map(function (t) {
            return { value: t.slug, label: t.label, note: t.inspiration, selected: t.slug === row.slug };
        })));
        this._palette.hold(this._list(lists, "palettes", "Colours for " + row.label, ROLE.PALETTE, "palette", ThemeWidget._offered(row).map(function (c) {
            var p = self._paletteOf(c.palette);
            return { value: c.palette, label: p.label, note: c.own ? "the design's own" : p.inspiration, selected: c.palette === worn.palette };
        })));
    }

    _list(b, key, ariaLabel, role, kind, options) {
        var self = this;
        var ul = b.createElement(key, "div");
        css.addClass(ul, pv_options);
        ul.setAttribute("role", "listbox");
        ul.setAttribute("aria-label", ariaLabel);
        options.forEach(function (o, i) {
            // each the house's choice option, cast as a style or a palette: its name and its note its own; placed by the widget
            var option = new ChoiceOption(b.createBranch(key + "-" + i), { label: o.label, note: o.note || null, selected: !!o.selected }, role);
            css.addClass(option.el, pv_option);
            option.setOnClick(new ThemePicked(self, kind, o.value));
            ul.appendChild(option.el);
        });
        return ul;
    }

    dispose() { this._stop(); this._style.dispose(); this._palette.dispose(); }
}
