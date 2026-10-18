package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** glassmorphism's implementation of choice-option_style: as its component standalone, by delegation. */
public record ChoiceOptionStyle() implements RootDelegation<BaseScheme, Glassmorphism, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseItems.ChoiceOption, RoleRoot> to() { return new ChoiceOption(); }
}
