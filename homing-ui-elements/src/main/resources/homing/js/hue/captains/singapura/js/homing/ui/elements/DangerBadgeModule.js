// =============================================================================
// DangerBadge — a short word saying something is wrong, or cannot be undone. A Text (a simple component) on a <span>, in the danger badge's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new DangerBadge(branch.elementMaker("x"), new FixedDangerBadge(), me.place(FixedDangerBadge));   in a slot
//   var t = new DangerBadge(branch.elementMaker("x"), new FixedDangerBadge());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class DangerBadge extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedDangerBadge, place ?? manageDangerBadge); }
}
