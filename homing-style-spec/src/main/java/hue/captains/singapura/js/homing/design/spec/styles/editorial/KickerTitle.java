package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's implementation of kicker_title: as its component standalone, by delegation. */
public record KickerTitle() implements RootDelegation<BaseScheme, Editorial, HouseText.Kicker, HouseSaying.Title> {
    @Override public CastingImplementation<BaseScheme, Editorial, HouseText.Kicker, RoleRoot> to() { return new Kicker(); }
}
