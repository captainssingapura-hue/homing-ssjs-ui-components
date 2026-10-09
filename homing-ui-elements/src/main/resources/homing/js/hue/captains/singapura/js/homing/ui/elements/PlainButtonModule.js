// =============================================================================
// PlainButton — the plain button: a Button (a simple component) in the plain look.
// Plain is not a semantic word, so it has no extent.
//
//   var b = new PlainButton(branch.createElement("go", PlainButton.TAG), { label: "Go", onClick: fn }, ROLE.STANDALONE);
//   b.enabled  b.size  b.label
// =============================================================================

class PlainButton extends Button {
    static LEAF = "plain-button";
}
