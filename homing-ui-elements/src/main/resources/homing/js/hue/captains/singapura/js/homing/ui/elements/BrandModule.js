// =============================================================================
// Brand — the site's own: its mark and its name, the way home. A branch
// component: its owner makes a sub-branch for it and hands it in.
//
//   var brand = new Brand(branch, { label, home }, role?)
//     label   the site's name, in the house's wordmark, cast as the brand's name
//     home    where it goes, through the href manager; "/" unless said
//     role    one of ROLE - Home, as a site's bar carries it; Standalone unless said
//   brand.root    the <a>
//
// Its colour is its mark's - a square of it, until a site gives a logo - and
// its name is in the ink of what it stands on. `css` is injected with the
// styles import; the href manager comes in by its explicit import.
// =============================================================================

const _brandOwner = Object.freeze({ toString: () => "brand" });

class Brand {
    static LEAF = "brand";

    constructor(branch, props, role) {
        if (!branch) throw new Error("[Brand] a branch of its own is required");
        var p = props || {};
        branch.activate(_brandOwner);
        var look = new Look(Brand, role || ROLE.STANDALONE);
        var a = branch.createElement("brand", "a");
        css.addClass(a, el_brand);
        look.wear(a, { except: ["color-background"] });
        HrefManagerInstance.set(a, p.home || "/");
        var mark = branch.createElement("mark", "span");
        css.addClass(mark, el_brand_mark);
        look.wear(mark, { only: ["color-background"] });   // its colour is its mark's
        mark.setAttribute("aria-hidden", "true");
        var word = new Wordmark(WordmarkFactory.create(branch, "word"), { text: p.label || "site" }, ROLE.NAME);
        a.appendChild(mark);
        a.appendChild(word.el);
        this.root = a;
    }
}
