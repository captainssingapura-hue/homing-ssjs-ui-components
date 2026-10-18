package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** neumorphism's implementation of choice-option_style: as its component standalone, by delegation. */
public record ChoiceOptionStyle() implements RootDelegation<BaseScheme, Neumorphism, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseItems.ChoiceOption, RoleRoot> to() { return new ChoiceOption(); }
}
