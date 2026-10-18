package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's implementation of kicker_prompt: as its component standalone, by delegation. */
public record KickerPrompt() implements RootDelegation<BaseScheme, Base, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public CastingImplementation<BaseScheme, Base, HouseText.Kicker, RoleRoot> to() { return new Kicker(); }
}
