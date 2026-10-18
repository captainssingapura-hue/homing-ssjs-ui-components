package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** neo-brutalism's implementation of choice-option_style: as its component standalone, by delegation. */
public record ChoiceOptionStyle() implements RootDelegation<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption, RoleRoot> to() { return new ChoiceOption(); }
}
