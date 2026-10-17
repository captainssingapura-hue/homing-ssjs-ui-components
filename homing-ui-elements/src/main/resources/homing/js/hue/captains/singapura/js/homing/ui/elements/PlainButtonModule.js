// =============================================================================
// PlainButton — the plain button: a Button (a simple component) in the plain look.
// Plain is not a semantic word, so it has no extent.
//
//   var b = new PlainButton(PlainButtonFactory.create(branch, "go"), { label: "Go" }, ROLE.STANDALONE);
//   b.setOnClick(handler)
//   b.enabled  b.size  b.label
// =============================================================================

class PlainButton extends Button {
    static LEAF = "plain-button";
}

/** Its companion: makes the <button> it is realized on, on its owner's branch, under its owner's name. */
const PlainButtonFactory = new SimpleComponentFactory(PlainButton);
