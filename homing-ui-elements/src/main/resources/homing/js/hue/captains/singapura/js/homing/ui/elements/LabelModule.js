// =============================================================================
// Label — words that name a control or a field, so the user knows what using it will touch. A Text (a simple component) on a <span>, in the label's look.
//
//   var x = new Label(LabelFactory.create(branch, "label"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Label extends Text {
    static LEAF = "label";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const LabelFactory = new SimpleComponentFactory(Label);
