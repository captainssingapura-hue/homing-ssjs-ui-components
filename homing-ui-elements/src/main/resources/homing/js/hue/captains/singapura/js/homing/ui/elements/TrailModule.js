// =============================================================================
// Trail — the way from a site's home to where the user is, each step a way
// back. A composed component on the style manager (RFC 0071): it takes a branch
// of its own and makes on it its <nav>; each time it is drawn, its steps on a
// branch of their own - each the house's crumb, cast as a step, the last the
// page and the others ways back, and between two the house's delimiter, cast as
// the break between them - each placed along its slot by a placement its own
// reference gives.
//
//   var trail = new Trail(branch.createBranch("trail"), new FixedTrail(), me.slot(SLOT_…));
//   trail.root          the <nav>
//   trail.draw(crumbs)  [ { text, to? } ], the last the page itself; a crumb with no
//                       `to` a heading of the trail, never a link - drawn afresh
//
// Its look - quieter than what it stands on, caption-sized - is the style's.
// `css` is injected with the styles import.
// =============================================================================

const _trailOwner = Object.freeze({ toString: () => "trail" });

class Trail extends SimpleComponent {
    static TAG = "nav";
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[Trail] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_trailOwner);
        super(branch.elementMaker("trail"), fixed, FixedTrail, place ?? manageTrail);
        this._branch = branch;
        this._drawn = 0;
        this._steps = null;
        css.addClass(this.el, el_trail);
        this.el.setAttribute("aria-label", "Breadcrumb");
        this.root = this.el;
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
                var sep = new Delimiter(this._steps.elementMaker("sep-" + i), new FixedDelimiter(), this._slot(SLOT_TRAIL_DELIMITER_BREAK));
                this.root.appendChild(sep.el);
            }
            var last = i === crumbs.length - 1, linked = !last && typeof crumbs[i].to === "string";
            var crumb = new Crumb(this._steps.elementMaker("crumb-" + i), new FixedCrumb(), this._slot(SLOT_TRAIL_CRUMB_STEP));
            crumb.text = crumbs[i].text;
            if (linked) crumb.to = crumbs[i].to;
            crumb.current = last;
            css.addClass(crumb.el, el_trail_crumb);
            this.root.appendChild(crumb.el);
        }
        return this;
    }
}
