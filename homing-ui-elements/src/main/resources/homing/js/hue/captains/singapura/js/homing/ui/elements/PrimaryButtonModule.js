// =============================================================================
// PrimaryButton — the primary button: a Button (a simple component) in the primary look,
// which also has an extent - how much of its word it wears.
//
//   var b = new PrimaryButton(branch.createElement("go", PrimaryButton.TAG), { label: "Go", onClick: fn }, ROLE.STANDALONE);
//   b.enabled  b.size  b.label  b.extent
// =============================================================================

class PrimaryButton extends Button {
    static LEAF = "primary-button";
    static LOOK = Object.freeze([el_button, el_button_primary]);
    static DEGREES = Object.freeze(["size", "extent"]);

    get extent() { return this._degrees.extent; }
    set extent(t) { this._degree("extent", t); }
}
