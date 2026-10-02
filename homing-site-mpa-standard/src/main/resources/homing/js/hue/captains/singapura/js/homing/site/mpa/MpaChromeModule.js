// =============================================================================
// MpaChrome — the page chrome: a bar with the brand, the trail and the
// preferences button, over the slot the app is mounted in. A branch
// component: the scaffold makes a branch for it and hands it in.
//
//   new MpaChrome(branch, root, chrome)
//     .main      the slot element the app is mounted in
//     .keyboard  the page's KeyboardSteward, one per document — the manager's
//                instance, since the keyboard is the page's, not an app's. The
//                scaffold hands it to the app as params.keyboard; the bar's own
//                dialog takes it too
//     .trail     the page's trail, for the app to carry on: extend(crumbs) -
//                crumbs past the ones the router wrote, [ { text, to? } ], the
//                last the page. The scaffold hands it to the app as params.trail
//
// `chrome` is what the server stamped into the page, frozen:
//   { brand: { label, home }, crumbs: [ { text, to } ], preferences: { module } }
// The crumbs are the trail the router wrote down; the last one is this
// page and carries no link. No crumbs means the address said nothing about
// position, and the bar shows the brand alone.
//
// Carried on, the crumbs are the router's, then the app's: a place inside the
// page that the server never sees - an anchor - is the app's to say, after
// the trail, where the server's knowledge ends. The page's own crumb then
// links to it; a crumb with no `to` is a heading, never a link. The document
// is titled by the last crumb.
//
// Everything the bar draws is on the chrome's branch; the app gets its own
// slot and never touches the bar. `css` is injected with the styles import;
// the href manager comes in by its explicit import.
// =============================================================================

const _chromeOwner = Object.freeze({ toString: () => "mpaChrome" });

class MpaChrome {
    constructor(branch, root, chrome) {
        if (!branch) throw new Error("[MpaChrome] a branch of its own is required");
        branch.activate(_chromeOwner);

        var column = branch.createElement("column", "div");
        css.addClass(column, mpa_root);

        var bar = branch.createElement("bar", "header");
        css.addClass(bar, mpa_header);
        bar.appendChild(MpaChrome._brand(branch, chrome && chrome.brand));
        this._branch = branch;
        this._label = (chrome && chrome.brand && chrome.brand.label) || "site";
        this._stamped = (chrome && chrome.crumbs) || [];
        this._trails = 0;
        this._trail = null;
        this._nav = branch.createElement("crumbs", "nav");
        css.addClass(this._nav, mpa_crumbs);
        this._nav.setAttribute("aria-label", "Breadcrumb");
        this._drawTrail(this._stamped);
        bar.appendChild(this._nav);
        var self = this;
        this.trail = Object.freeze({ extend: function (more) { self.extendTrail(more); } });
        this.keyboard = KeyboardStewardInstance;
        this.preferences = new PreferencesButton(branch.createBranch("prefs"), bar, chrome, this.keyboard);
        column.appendChild(bar);

        var main = branch.createElement("main", "main");
        css.addClass(main, mpa_main);
        column.appendChild(main);

        root.replaceChildren(column);
        this.el = column;
        this.main = main;
    }

    static _brand(branch, brand) {
        var a = branch.createElement("brand", "a");
        css.addClass(a, mpa_brand);
        HrefManagerInstance.set(a, (brand && brand.home) || "/");
        var mark = branch.createElement("brandMark", "span");
        css.addClass(mark, mpa_brand_mark);
        var word = branch.createElement("brandWord", "span");
        css.addClass(word, mpa_brand_word);
        word.textContent = (brand && brand.label) || "site";
        a.appendChild(mark);
        a.appendChild(word);
        return a;
    }

    /** The router's crumbs, then these: the last the page, and the document titled by it. */
    extendTrail(more) {
        if (!Array.isArray(more)) throw new TypeError("[MpaChrome] extendTrail takes an array of crumbs, got " + JSON.stringify(more));
        more.forEach(function (c, i) {
            if (!c || typeof c.text !== "string" || !c.text.trim()) throw new TypeError("[MpaChrome] extendTrail: crumbs[" + i + "] has no text");
        });
        var all = this._stamped.concat(more);
        this._drawTrail(all);
        if (all.length) document.title = all[all.length - 1].text + " · " + this._label;
    }

    /** The crumbs drawn afresh, on a branch of their own: the last the page, the others links - but a heading's, which has no `to`. */
    _drawTrail(crumbs) {
        if (this._trail) this._trail.dissolve();
        this._trail = this._branch.createBranch("trail" + (++this._trails));
        this._trail.activate(_chromeOwner);
        for (var i = 0; i < crumbs.length; i++) {
            if (i > 0) {
                var sep = this._trail.createElement("sep-" + i, "span");
                css.addClass(sep, mpa_crumb_sep);
                sep.textContent = "/";
                sep.setAttribute("aria-hidden", "true");
                this._nav.appendChild(sep);
            }
            var last = i === crumbs.length - 1, linked = !last && typeof crumbs[i].to === "string";
            var el = this._trail.createElement("crumb-" + i, linked ? "a" : "span");
            css.addClass(el, mpa_crumb);
            el.textContent = crumbs[i].text;
            if (last) el.setAttribute("aria-current", "page");
            if (linked) HrefManagerInstance.set(el, crumbs[i].to);
            this._nav.appendChild(el);
        }
    }
}
