package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's implementation of heading4_title: as its component standalone, by delegation. */
public record Heading4Title() implements RootDelegation<BaseScheme, Base, HouseText.Heading4, HouseSaying.Title> {
    @Override public CastingImplementation<BaseScheme, Base, HouseText.Heading4, RoleRoot> to() { return new Heading4(); }
}
