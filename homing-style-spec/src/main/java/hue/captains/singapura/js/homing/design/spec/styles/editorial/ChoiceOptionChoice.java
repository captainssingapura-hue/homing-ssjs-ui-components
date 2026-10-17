package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** editorial's implementation of choice-option_choice: as its component standalone, by delegation. */
public record ChoiceOptionChoice() implements CastingStylePerRole<BaseScheme, Editorial, HouseItems.ChoiceOption, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, Editorial, HouseItems.ChoiceOption, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new ChoiceOption());
    }
}
