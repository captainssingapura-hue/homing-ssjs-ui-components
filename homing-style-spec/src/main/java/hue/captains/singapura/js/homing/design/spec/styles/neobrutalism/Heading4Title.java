package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's implementation of heading4_title: as its component standalone, by delegation. */
public record Heading4Title() implements RootDelegation<BaseScheme, NeoBrutalism, HouseText.Heading4, HouseSaying.Title> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseText.Heading4, RoleRoot> to() { return new Heading4(); }
}
