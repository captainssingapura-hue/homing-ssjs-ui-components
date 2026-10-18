package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's implementation of kicker_prompt: as its component standalone, by delegation. */
public record KickerPrompt() implements RootDelegation<BaseScheme, Neumorphism, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseText.Kicker, RoleRoot> to() { return new Kicker(); }
}
