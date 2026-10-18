package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** editorial's implementation of choice-option_palette: as its component standalone, by delegation. */
public record ChoiceOptionPalette() implements RootDelegation<BaseScheme, Editorial, HouseItems.ChoiceOption, HouseDoing.Palette> {
    @Override public CastingImplementation<BaseScheme, Editorial, HouseItems.ChoiceOption, RoleRoot> to() { return new ChoiceOption(); }
}
