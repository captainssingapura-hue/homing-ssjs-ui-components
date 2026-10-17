// =============================================================================
// SuccessBadge — a short word saying it went well. A Text (a simple component) on a <span>, in the success badge's look.
//
//   var x = new SuccessBadge(SuccessBadgeFactory.create(branch, "success-badge"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class SuccessBadge extends Text {
    static LEAF = "success-badge";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const SuccessBadgeFactory = new SimpleComponentFactory(SuccessBadge);
