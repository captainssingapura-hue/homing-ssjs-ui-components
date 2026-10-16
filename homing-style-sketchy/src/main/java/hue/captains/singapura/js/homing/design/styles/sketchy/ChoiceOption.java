package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The choice option in sketchy: one choice of a setting - tinted under the hand, ringed by the focus, filled while
 * picked. Whole: every property its look has (D9).
 */
public record ChoiceOption() implements CastingStyle<BaseScheme, Sketchy, HouseItems.ChoiceOption, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseItems.ChoiceOption> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(),
                new BaseColours.Ring(), new BaseColours.AccentWashSoft());
    }
}
