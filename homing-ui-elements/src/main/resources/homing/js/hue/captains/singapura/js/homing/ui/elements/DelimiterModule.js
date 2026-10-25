// =============================================================================
// Delimiter — a mark between the steps of a run, on one line with them: the
// sign between a trail's crumbs. A simple component (SimpleComponent) on a
// <span>; its sign (a slash, unless a style draws
// another) is drawn by the style, never written by its owner. It says nothing
// to whoever cannot see it (aria-hidden): the steps say where they are. It has
// no state and offers nothing.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var m = new Delimiter(branch.elementMaker("x"), new FixedDelimiter(), me.place(FixedDelimiter));
// =============================================================================

class Delimiter extends SimpleComponent {
    static TAG = "span";
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedDelimiter, place ?? manageDelimiter);
        this.el.setAttribute("aria-hidden", "true");
    }
}
