// =============================================================================
// SecondaryButton — the secondary button: a Button (a simple component) in the secondary look,
// which also has an extent - how much of its word it wears.
//
//   var b = new SecondaryButton(branch.createElement("go", SecondaryButton.TAG), { label: "Go", onClick: fn }, ROLE.STANDALONE);
//   b.enabled  b.size  b.label  b.extent
// =============================================================================

class SecondaryButton extends Button {
    static LEAF = "secondary-button";
    static DEGREES = Object.freeze(["size", "extent"]);

    get extent() { return this._degrees.extent; }
    set extent(t) { this._degree("extent", t); }
}
