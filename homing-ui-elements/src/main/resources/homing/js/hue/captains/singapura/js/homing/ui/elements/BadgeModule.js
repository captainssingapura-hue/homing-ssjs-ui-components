// =============================================================================
// Badge — a short word that sorts or flags what it is beside. A Text (a simple component) on a <span>, in the badge's look.
//
//   var x = new Badge(BadgeFactory.create(branch, "badge"), { text: "…" }, ROLE.STANDALONE);
//   x.text  x.size
// =============================================================================

class Badge extends Text {
    static LEAF = "badge";
    static TAG = "span";
}

/** Its companion: makes the <span> it is realized on, on its owner's branch, under its owner's name. */
const BadgeFactory = new SimpleComponentFactory(Badge);
