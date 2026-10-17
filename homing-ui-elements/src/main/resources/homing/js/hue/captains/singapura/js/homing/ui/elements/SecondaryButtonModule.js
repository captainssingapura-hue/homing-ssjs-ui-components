// =============================================================================
// SecondaryButton — the secondary button: a Button (a simple component) in the secondary look,
// which also has an extent - how much of its word it wears.
//
//   var b = new SecondaryButton(SecondaryButtonFactory.create(branch, "go"), { label: "Go" }, ROLE.STANDALONE);
//   b.setOnClick(handler)
//   b.enabled  b.size  b.label  b.extent
// =============================================================================

class SecondaryButton extends Button {
    static LEAF = "secondary-button";
    static DEGREES = Object.freeze(["size", "extent"]);

    get extent() { return this._degrees.extent; }
    set extent(t) { this._degree("extent", t); }
}

/** Its companion: makes the <button> it is realized on, on its owner's branch, under its owner's name. */
const SecondaryButtonFactory = new SimpleComponentFactory(SecondaryButton);
