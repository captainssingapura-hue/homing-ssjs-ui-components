// =============================================================================
// SiteBar — heads a site's pages: its brand at the start, the way to where the
// user is across the middle, and what its owner hands it at its end - a site's
// preferences - in the ink written on it, which what it carries takes. A
// branch component: its owner makes a sub-branch for it and hands it in.
//
//   var bar = new SiteBar(branch, { brand: { label, home }, crumbs })
//   bar.root          the bar, a <header>
//   bar.trail         its trail: trail.draw(crumbs) draws the way afresh
//   bar.end(c)        c at its end: a component, by its element (el) or its tree (root)
//
// Its brand is the house's, cast as the way home; its trail the house's, cast
// as the path. The bar's look is the house's; its classes only place. `css` is
// injected with the styles import.
// =============================================================================

const _siteBarOwner = Object.freeze({ toString: () => "siteBar" });

class SiteBar {
    static LEAF = "site-bar";

    constructor(branch, props) {
        if (!branch) throw new Error("[SiteBar] a branch of its own is required");
        var p = props || {};
        branch.activate(_siteBarOwner);
        var el = branch.createElement("bar", "header");
        css.addClass(el, el_site_bar);
        new Look(SiteBar, ROLE.STANDALONE).wear(el);
        this.root = el;
        this._hold(new Brand(branch.createBranch("brand"), p.brand || {}, ROLE.HOME), el_site_bar_start);
        this.trail = new Trail(branch.createBranch("trail"), { crumbs: p.crumbs || [] }, ROLE.PATH);
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
