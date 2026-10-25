// =============================================================================
// SiteBar — heads a site's pages: its brand at the start, the way to where the
// user is across the middle, and what its owner hands it at its end - a site's
// preferences - in the ink written on it, which what it carries takes. A
// composed component on the style manager (RFC 0071): it takes a branch of its
// own and makes on it its <header> and its parts - its brand, the house's, cast
// as the way home; its trail, the house's, cast as the path - each placed along
// its slot by its own reference.
//
//   var bar = new SiteBar(branch.createBranch("bar"), new FixedSiteBar(), me.place(FixedSiteBar));
//   bar.root          the bar, a <header>
//   bar.brand         its brand: bar.brand.label, bar.brand.home
//   bar.trail         its trail: trail.draw(crumbs) draws the way afresh
//   bar.end(c)        c at its end: a component, by its element (el) or its tree (root)
//
// The bar's classes only place. `css` is injected with the styles import.
// =============================================================================

const _siteBarOwner = Object.freeze({ toString: () => "siteBar" });

class SiteBar extends SimpleComponent {
    static TAG = "header";
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[SiteBar] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_siteBarOwner);
        super(branch.elementMaker("bar"), fixed, FixedSiteBar, place ?? manageSiteBar);
        css.addClass(this.el, el_site_bar);
        this.root = this.el;
        this.brand = new Brand(branch.createBranch("brand"), new FixedBrand(), this._place(FixedBrand));
        this._hold(this.brand, el_site_bar_start);
        this.trail = new Trail(branch.createBranch("trail"), new FixedTrail(), this._place(FixedTrail));
        this._hold(this.trail, el_site_bar_way);
    }

    end(c) { return this._hold(c, el_site_bar_end); }

    _hold(c, place) {
        var e = c && (c.root || c.el);
        if (!e) throw new Error("[SiteBar] holds a component: one with its element, el, or its tree, root");
        css.addClass(e, place);
        this.root.appendChild(e);
        return this;
    }
}
