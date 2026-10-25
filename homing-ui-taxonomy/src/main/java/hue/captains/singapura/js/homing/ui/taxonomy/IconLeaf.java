package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Leaf;

/**
 * An icon (RFC 0067, D52, D56): a leaf of the Icon branch - one meaning, said in its meaning as every node's is, the same
 * in every style. It is no content, and says nothing of how it is drawn: each style's icon palette draws it, a picture
 * for each icon. Every leaf the instance registers under the Icon branch is one, the house's and every downstream's.
 */
public interface IconLeaf extends Leaf<HouseBranches.Icon> {

    @Override default HouseBranches.Icon parent() { return HouseBranches.Icon.INSTANCE; }
}
