// =============================================================================
// DropMark — Where a dragged thing would land: a bar of the accent, its owner placing it where
// the thing would go, and only while a drag is on. A simple component on a <div>, in the drop mark's look;
// a mark is seen, never pressed, and says nothing to whoever cannot see it.
//
//   var mark = new DropMark(branch.elementMaker("drop"), {}, ROLE.LANDING);
//   mark.el
// =============================================================================

class DropMark extends SimpleComponent {
    static LEAF = "drop-mark";
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(make, props, role) {
        super(make, props, role);
        this.el.setAttribute("aria-hidden", "true");
    }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const DropMarkFactory = new SimpleComponentFactory(DropMark);
