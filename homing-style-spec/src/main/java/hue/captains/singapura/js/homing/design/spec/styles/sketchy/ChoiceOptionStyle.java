package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** sketchy's implementation of choice-option_style: as its component standalone, by delegation. */
public record ChoiceOptionStyle() implements CastingStylePerRole<BaseScheme, Sketchy, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseItems.ChoiceOption, HouseDoing.Style> style() {
        return new StylingRes.RootDelegation<>(new ChoiceOption());
    }
}
