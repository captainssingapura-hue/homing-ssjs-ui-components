// =============================================================================
// Trail — the way from a site's home to where the user is, each step a way
// back. A branch component: its owner makes a sub-branch for it and hands it in.
//
//   var trail = new Trail(branch, { crumbs }, role?)
//     crumbs   [ { text, to? } ], the last the page itself; a crumb with no `to`
//              is a heading of the trail, never a link
//     role     one of ROLE - Path, as a site's bar carries it; Standalone unless said
//   trail.root          the <nav>
//   trail.draw(crumbs)  the crumbs drawn afresh, on a branch of their own
//
// Each step is the house's crumb, cast as a step - the last the page, the
// others ways back - and between two, the house's delimiter, cast as the break
// between them. Its look - quieter than what it stands on, caption-sized - is
// the house's. `css` is injected with the styles import.
// =============================================================================

const _trailOwner = Object.freeze({ toString: () => "trail" });

class Trail {
    static LEAF = "trail";

    constructor(branch, props, role) {
        if (!branch) throw new Error("[Trail] a branch of its own is required");
        branch.activate(_trailOwner);
        this._branch = branch;
        this._drawn = 0;
        this._steps = null;
        var nav = branch.createElement("trail", "nav");
        css.addClass(nav, el_trail);
        new Look(Trail, role || ROLE.STANDALONE).wear(nav);
        nav.setAttribute("aria-label", "Breadcrumb");
        this.root = nav;
        this.draw((props && props.crumbs) || []);
    }

    draw(crumbs) {
        if (!Array.isArray(crumbs)) throw new TypeError("[Trail] crumbs are an array, got " + JSON.stringify(crumbs));
        crumbs.forEach(function (c, i) {
            if (!c || typeof c.text !== "string" || !c.text.trim()) throw new TypeError("[Trail] crumbs[" + i + "] has no text");
        });
        if (this._steps) this._steps.dissolve();
        this._steps = this._branch.createBranch("steps" + (++this._drawn));
        this._steps.activate(_trailOwner);
        while (this.root.firstChild) this.root.removeChild(this.root.firstChild);
        for (var i = 0; i < crumbs.length; i++) {
            if (i > 0) {
                var sep = new Delimiter(DelimiterFactory.create(this._steps, "sep-" + i), {}, ROLE.BREAK);
                this.root.appendChild(sep.el);
            }
            var last = i === crumbs.length - 1, linked = !last && typeof crumbs[i].to === "string";
            var crumb = new Crumb(CrumbFactory.create(this._steps, "crumb-" + i),
                                  { text: crumbs[i].text, to: linked ? crumbs[i].to : null, current: last }, ROLE.STEP);
            css.addClass(crumb.el, el_trail_crumb);
            this.root.appendChild(crumb.el);
        }
        return this;
    }
}
