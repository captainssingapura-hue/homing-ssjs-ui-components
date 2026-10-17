// =============================================================================
// Ground — the surface a page stands on: its ground, the ink and the face of
// what is written on it, and its scrollbar. An element component on the
// document's own element, worn once, by the page: everything on the page
// stands on it, and a text takes its ink and its face from it.
//
//   new Ground(el)     el: the document's element, <html>
//
// Its look is the house's, the theme's answer for the ground; it has no state
// and nothing happens to it.
// =============================================================================

class Ground {
    static LEAF = "ground";
    static TAG = "html";

    constructor(el) {
        if (!el) throw new Error("[Ground] the document's element is required");
        new Look(Ground, ROLE.STANDALONE).wear(el);
        this.el = el;
    }
}
