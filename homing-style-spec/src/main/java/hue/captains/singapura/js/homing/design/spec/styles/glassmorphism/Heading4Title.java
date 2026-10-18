package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of heading4_title: as its component standalone, by delegation. */
public record Heading4Title() implements RootDelegation<BaseScheme, Glassmorphism, HouseText.Heading4, HouseSaying.Title> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseText.Heading4, RoleRoot> to() { return new Heading4(); }
}
