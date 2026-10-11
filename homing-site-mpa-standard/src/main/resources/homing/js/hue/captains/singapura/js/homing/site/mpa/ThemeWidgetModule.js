// =============================================================================
// ThemeWidget — the theme as a preference. new ThemeWidget(branch, params) → root, dispose
//   params: { name: "theme", label, summary? }
//
// Two listboxes from /themes: the designs, and the colours the chosen design
// is offered in. A design picked keeps the colours worn now when they fit it
// (its own, or a palette that says it suits it), else takes its own colours.
// A pick is field.set(slug) — the steward's remember — and the CSS manager,
// which follows the store, switches every sheet; the widget follows the store
// as its field does, so the marks and the note move together.
// =============================================================================

const _owner = Object.freeze({ toString: () => "themeWidget" });

class ThemeWidget {
    constructor(branch, params) {
        branch.activate(_owner);
        var self = this;
        this._branch = branch;
        this._reg = null;
        this._seq = 0;
        this._listsName = null;
        this._field = new PreferenceField(branch.createBranch("field"), Object.assign({}, params, { kicker: "theme" }));
        this.root = this._field.root;
        this._stop = PreferenceViewInstance.onChange(function () { self._draw(self._field.value()); });

        // what it is waiting for, in the house's kicker, cast as its status
        var pending = new Kicker(KickerFactory.create(branch, "pending"), { text: "Loading the site's themes…" }, ROLE.STATUS);
        css.addClass(pending.el, pv_kicker);
        this._field.hold(pending.el);

        fetch("/themes").then(function (r) { if (!r.ok) throw new Error("/themes HTTP " + r.status); return r.json(); })
            .then(function (j) { self._reg = j; pending.el.remove(); self._draw(self._field.value()); })
            .catch(function (e) { pending.text = "The site's themes could not be loaded: " + e.message; });
    }

    _decompose(slug) {
        var reg = this._reg;
        if (!reg || !slug) return null;
        for (var i = 0; i < reg.themes.length; i++) {
            var t = reg.themes[i];
            for (var j = 0; j < t.colours.length; j++) if (t.colours[j].slug === slug) return { base: t, colour: t.colours[j] };
        }
        return null;
    }
    _paletteOf(slug) {
        var reg = this._reg;
        for (var i = 0; i < reg.palettes.length; i++) if (reg.palettes[i].slug === slug) return reg.palettes[i];
        return { label: slug, inspiration: "" };
    }

    // Every draw is a fresh sub-branch: the lists are rebuilt whole, and the
    // names on the previous branch go with it.
    _draw(worn) {
        var reg = this._reg, self = this;
        if (!reg) return;
        worn = worn || reg.default;          // nothing kept and nothing pinned: the site's default is what the page wears
        if (this._listsName) this._branch.dissolveBranch(this._listsName);
        this._listsName = "lists" + (++this._seq);
        var listsBranch = this._branch.createBranch(this._listsName);
        listsBranch.activate(_owner);
        var at = this._decompose(worn) || { base: reg.themes[0], colour: null };

        // a design picked keeps the colours worn now when they fit it, else takes its own
        var wornPalette = at.colour ? at.colour.palette : null;
        this._field.hold(this._list(listsBranch, "designs", "Designs", ROLE.STYLE, reg.themes.map(function (t) {
            return { value: ThemeWidget._switchTo(t, wornPalette), label: t.label, note: t.inspiration, selected: t.slug === at.base.slug };
        })));
        var offered = at.base.colours.filter(function (c) { return c.own || c.fits; });
        if (offered.length > 1) {
            var k = new Kicker(KickerFactory.create(listsBranch, "coloursKicker"), { text: "Colours for " + at.base.label }, ROLE.SUBTITLE);
            css.addClass(k.el, pv_kicker);
            this._field.hold(k.el);
            this._field.hold(this._list(listsBranch, "colours", "Colours", ROLE.PALETTE, offered.map(function (c) {
                var p = self._paletteOf(c.palette);
                return { value: c.slug, label: p.label, note: c.own ? "the design's own" : p.inspiration, selected: c.slug === worn };
            })));
        }
    }

    /**
     * The theme a design is switched to: the design in the palette worn now, when that palette fits it - its own,
     * or one that says it suits it - else the design in its own colours.
     */
    static _switchTo(design, wornPalette) {
        var kept = wornPalette == null ? null
                 : design.colours.filter(function (c) { return c.palette === wornPalette && (c.own || c.fits); })[0];
        return kept ? kept.slug : design.slug;
    }

    _list(b, key, ariaLabel, role, options) {
        var self = this;
        var look = new Look(Object.freeze({ LEAF: "choice-option" }), role);   // each option's: a choice option, as a style or a palette
        var ul = b.createElement(key, "div");
        css.addClass(ul, pv_options);
        ul.setAttribute("role", "listbox");
        ul.setAttribute("aria-label", ariaLabel);
        options.forEach(function (o, i) {
            var row = b.createElement(key + "-" + i, "button");
            row.type = "button";
            css.addClass(row, pv_option);
            look.wear(row);
            row.setAttribute("role", "option");
            row.setAttribute("aria-selected", o.selected ? "true" : "false");
            var text = b.createElement(key + "-" + i + "-label", "span");
            text.textContent = o.label;
            row.appendChild(text);
            if (o.note) {
                var note = new Caption(CaptionFactory.create(b, key + "-" + i + "-note"), { text: o.note }, ROLE.NOTE);
                css.addClass(note.el, pv_option_note);
                row.appendChild(note.el);
            }
            row.addEventListener("click", function () { self._field.set(o.value); });
            ul.appendChild(row);
        });
        return ul;
    }

    dispose() { this._stop(); this._field.dispose(); }
}
