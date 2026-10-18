package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** retro-futurism's implementation of choice-option_style: as its component standalone, by delegation. */
public record ChoiceOptionStyle() implements RootDelegation<BaseScheme, RetroFuturism, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public CastingImplementation<BaseScheme, RetroFuturism, HouseItems.ChoiceOption, RoleRoot> to() { return new ChoiceOption(); }
}
