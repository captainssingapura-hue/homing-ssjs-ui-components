package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of caption_hint: as its component standalone, by delegation. */
public record CaptionHint() implements RootDelegation<BaseScheme, Glassmorphism, HouseText.Caption, HouseSaying.Hint> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseText.Caption, RoleRoot> to() { return new Caption(); }
}
