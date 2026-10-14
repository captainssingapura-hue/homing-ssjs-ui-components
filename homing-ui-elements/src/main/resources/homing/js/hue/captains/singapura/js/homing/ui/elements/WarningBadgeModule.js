// =============================================================================
// WarningBadge — a short word asking for care before going on. A Text (a simple component) on a <span>, in the warning badge's look.
//
//   var x = new WarningBadge(WarningBadgeFactory.create(branch, "warning-badge"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class WarningBadge extends Text {
    static LEAF = "warning-badge";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const WarningBadgeFactory = new SimpleComponentFactory(WarningBadge);
