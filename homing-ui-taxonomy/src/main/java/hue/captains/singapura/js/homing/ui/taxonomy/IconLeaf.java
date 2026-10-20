package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Leaf;

/**
 * An icon (RFC 0067, D52, D56): a leaf of the Icon branch, which says what it shows - its content, the same in every
 * style. Every leaf the instance registers under the Icon branch is one, the house's and every downstream's.
 */
public interface IconLeaf extends Leaf<HouseBranches.Icon> {

    /** What it shows: which picture, named. */
    IconContent content();

    @Override default HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
}
