// =============================================================================
// Separator — a line between the rows of a list or a menu: one group ruled off
// from the next. A simple component (SimpleComponent) on a <div>, in the
// separator's look - how thick the line is, and its colour, are its style's
// and its palette's. It is a separator to whoever cannot see it
// (role="separator"); it shows no words, has no state, and offers nothing.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var m = new Separator(branch.elementMaker("x"), new FixedSeparator(), me.place(FixedSeparator));
// =============================================================================

class Separator extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedSeparator, place ?? manageSeparator);
        this.el.setAttribute("role", "separator");
    }
}
