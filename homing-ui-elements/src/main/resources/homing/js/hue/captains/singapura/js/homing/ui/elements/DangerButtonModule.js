// =============================================================================
// DangerButton — the danger button: a Button (a simple component) in the danger look,
// which also has an extent - how much of its word it wears.
//
//   var b = new DangerButton(branch.createElement("go", DangerButton.TAG), { label: "Go", onClick: fn }, ROLE.STANDALONE);
//   b.enabled  b.size  b.label  b.extent
// =============================================================================

class DangerButton extends Button {
    static LEAF = "danger-button";
    static LOOK = Object.freeze([el_button, el_button_danger]);
    static DEGREES = Object.freeze(["size", "extent"]);

    get extent() { return this._degrees.extent; }
    set extent(t) { this._degree("extent", t); }
}
