// =============================================================================
// Delimiter — a mark between the steps of a run, on one line with them: the
// sign between a trail's crumbs. A simple component (SimpleComponent) on a
// <span>, in the delimiter's look - its sign (a slash, unless a style draws
// another) is drawn by the look, never written by its owner. It says nothing
// to whoever cannot see it (aria-hidden): the steps say where they are. It has
// no state and offers nothing:
//
//   var d = new Delimiter(DelimiterFactory.create(branch, "sep-1"), {}, ROLE.BREAK);
//
// The room either side of it is its owner's, by its own placement.
// =============================================================================

class Delimiter extends SimpleComponent {
    static LEAF = "delimiter";
    static TAG = "span";
    static BEHAVIOR = MarkBehavior;

    constructor(el, props, role) {
        super(el, props, role);
        el.setAttribute("aria-hidden", "true");
    }
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const DelimiterFactory = new SimpleComponentFactory(Delimiter);
