// =============================================================================
// SuccessButton — the success button: a Button (a simple component) in the success look,
// which also has an extent - how much of its word it wears.
//
//   var b = new SuccessButton(SuccessButtonFactory.create(branch, "go"), { label: "Go" }, ROLE.STANDALONE);
//   b.setOnClick(handler)
//   b.enabled  b.size  b.label  b.extent
// =============================================================================

class SuccessButton extends Button {
    static LEAF = "success-button";
    static DEGREES = Object.freeze(["size", "extent"]);

    get extent() { return this._degrees.extent; }
    set extent(t) { this._degree("extent", t); }
}

/** Its companion: makes the <button> it is realized on, on its owner's branch, under its owner's name. */
const SuccessButtonFactory = new SimpleComponentFactory(SuccessButton);
