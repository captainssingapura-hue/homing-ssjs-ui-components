// =============================================================================
// Brand — the site's own: its mark and its name, the way home. A composed
// component on the style manager (RFC 0071): it takes a branch of its own and
// makes on it its <a> and its parts - its mark, the house's brand mark cast as
// its symbol: a square of its colour, until a site gives a logo; its name, the
// house's wordmark cast as its name, in the ink of what it stands on - each
// placed along its slot by its own reference.
//
//   var brand = new Brand(branch.createBranch("brand"), new FixedBrand(), me.place(FixedBrand));
//   brand.label = "Homing"   the site's name; "site" until given
//   brand.home = "/"         where it goes, through the href manager; "/" until given
//   brand.root               the <a>
//
// `css` is injected with the styles import; the href manager comes in by its
// explicit import.
// =============================================================================

const _brandOwner = Object.freeze({ toString: () => "brand" });

class Brand extends SimpleComponent {
    static TAG = "a";
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[Brand] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_brandOwner);
        super(branch.elementMaker("brand"), fixed, FixedBrand, place ?? manageBrand);
        css.addClass(this.el, el_brand);
        var mark = new BrandMark(branch.elementMaker("mark"), new FixedBrandMark(), this._place(FixedBrandMark));   // its colour is its mark's
        css.addClass(mark.el, el_brand_mark);
        this._word = new Wordmark(branch.elementMaker("word"), new FixedWordmark(), this._place(FixedWordmark));
        this.el.appendChild(mark.el);
        this.el.appendChild(this._word.el);
        this.root = this.el;
        this.label = null;
        this.home = null;
    }

    /** The site's name, its wordmark's words. */
    get label() { return this._word.text; }
    set label(words) { this._word.text = words == null || words === "" ? "site" : String(words); }

    /** Where it goes. */
    get home() { return this._home; }
    set home(way) {
        this._home = way == null || way === "" ? "/" : String(way);
        HrefManagerInstance.set(this.el, this._home);
    }
}
