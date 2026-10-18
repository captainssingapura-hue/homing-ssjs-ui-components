package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** base's implementation of choice-option_palette: as its component standalone, by delegation. */
public record ChoiceOptionPalette() implements RootDelegation<BaseScheme, Base, HouseItems.ChoiceOption, HouseDoing.Palette> {
    @Override public CastingImplementation<BaseScheme, Base, HouseItems.ChoiceOption, RoleRoot> to() { return new ChoiceOption(); }
}
