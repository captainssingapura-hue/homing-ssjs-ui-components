package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's implementation of wordmark_name: as its component standalone, by delegation. */
public record WordmarkName() implements RootDelegation<BaseScheme, Editorial, HouseText.Wordmark, HouseSaying.Name> {
    @Override public CastingImplementation<BaseScheme, Editorial, HouseText.Wordmark, RoleRoot> to() { return new Wordmark(); }
}
