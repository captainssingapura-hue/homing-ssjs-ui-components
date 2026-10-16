package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The choice option in neumorphism: one choice of a setting - tinted under the hand, ringed by the focus, filled while
 * picked. Whole: every property its look has (D9).
 */
public record ChoiceOption() implements CastingStyle<BaseScheme, Neumorphism, HouseItems.ChoiceOption, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseItems.ChoiceOption> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.OnInverted(), new HouseColours.Border(), new HouseColours.Title(),
                new HouseColours.Ring(), new HouseColours.AccentWashSoft());
    }
}
