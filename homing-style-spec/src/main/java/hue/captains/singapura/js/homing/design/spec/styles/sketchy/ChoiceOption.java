package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** sketchy's implementation of choice-option: CSS of its own. */
public record ChoiceOption() implements CastingStylePerRole<BaseScheme, Sketchy, HouseItems.ChoiceOption, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseItems.ChoiceOption, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.Ring(), new BaseColours.AccentWashSoft());
    }
}
