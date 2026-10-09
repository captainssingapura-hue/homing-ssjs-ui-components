// =============================================================================
// SuccessButton — the success button: a Button (a simple component) in the success look,
// which also has an extent - how much of its word it wears.
//
//   var b = new SuccessButton(branch.createElement("go", SuccessButton.TAG), { label: "Go", onClick: fn }, ROLE.STANDALONE);
//   b.enabled  b.size  b.label  b.extent
// =============================================================================

class SuccessButton extends Button {
    static LEAF = "success-button";
    static DEGREES = Object.freeze(["size", "extent"]);

    get extent() { return this._degrees.extent; }
    set extent(t) { this._degree("extent", t); }
}
