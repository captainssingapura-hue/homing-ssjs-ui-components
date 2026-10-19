// =============================================================================
// DropMark — Where a dragged thing would land: a bar of the accent, its owner placing it where
// the thing would go, and only while a drag is on. A simple component on a <div>;
// a mark is seen, never pressed, and says nothing to whoever cannot see it.
// On the style manager (RFC 0071): its module stamped with its header (D19),
// its states set through its reference.
//
//   var m = new DropMark(branch.elementMaker("x"), new FixedDropMark(), me.slot(SLOT_…));
// =============================================================================

class DropMark extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(make, fixed, place) {
        super(make, fixed, FixedDropMark, place ?? manageDropMark);
        this.el.setAttribute("aria-hidden", "true");
    }
}
