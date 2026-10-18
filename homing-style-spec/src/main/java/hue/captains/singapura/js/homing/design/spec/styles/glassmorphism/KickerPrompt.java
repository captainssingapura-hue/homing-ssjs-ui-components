package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of kicker_prompt: as its component standalone, by delegation. */
public record KickerPrompt() implements RootDelegation<BaseScheme, Glassmorphism, HouseText.Kicker, HouseSaying.Prompt> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseText.Kicker, RoleRoot> to() { return new Kicker(); }
}
