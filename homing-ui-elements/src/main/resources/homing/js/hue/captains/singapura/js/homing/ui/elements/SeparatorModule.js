// =============================================================================
// Separator — a line between the rows of a list or a menu: one group ruled off
// from the next. A simple component (SimpleComponent) on a <div>, in the
// separator's look - how thick the line is, and its colour, are its style's
// and its palette's. It is a separator to whoever cannot see it
// (role="separator"); it shows no words, has no state, and offers nothing:
//
//   var s = new Separator(SeparatorFactory.create(branch, "sep-1"), {}, ROLE.BREAK);
//
// The room above and below it is its owner's, by its own placement class.
// =============================================================================

class Separator extends SimpleComponent {
    static LEAF = "separator";
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(el, props, role) {
        super(el, props, role);
        el.setAttribute("role", "separator");
    }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const SeparatorFactory = new SimpleComponentFactory(Separator);
