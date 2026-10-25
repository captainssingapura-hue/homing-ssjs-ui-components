// =============================================================================
// Badge — a short word that sorts or flags what it is beside. A Text (a simple component) on a <span>, in the badge's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new Badge(branch.elementMaker("x"), new FixedBadge(), me.place(FixedBadge));   in a slot
//   var t = new Badge(branch.elementMaker("x"), new FixedBadge());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class Badge extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedBadge, place ?? manageBadge); }
}
