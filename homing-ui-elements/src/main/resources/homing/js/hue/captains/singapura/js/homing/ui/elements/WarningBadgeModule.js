// =============================================================================
// WarningBadge — a short word asking for care before going on. A Text (a simple component) on a <span>, in the warning badge's look.
// On the style manager (RFC 0071): its module stamped with its header (D19).
//
//   var t = new WarningBadge(branch.elementMaker("x"), new FixedWarningBadge(), me.place(FixedWarningBadge));   in a slot
//   var t = new WarningBadge(branch.elementMaker("x"), new FixedWarningBadge());                     at the root
//   t.text = "…"   t.size = -1
// =============================================================================

class WarningBadge extends Text {
    static TAG = "span";

    constructor(make, fixed, place) { super(make, fixed, FixedWarningBadge, place ?? manageWarningBadge); }
}
