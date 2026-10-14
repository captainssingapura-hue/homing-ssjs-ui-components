// =============================================================================
// DangerBadge — a short word saying something is wrong, or cannot be undone. A Text (a simple component) on a <span>, in the danger badge's look.
//
//   var x = new DangerBadge(DangerBadgeFactory.create(branch, "danger-badge"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class DangerBadge extends Text {
    static LEAF = "danger-badge";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const DangerBadgeFactory = new SimpleComponentFactory(DangerBadge);
