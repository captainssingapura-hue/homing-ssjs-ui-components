package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The tab in neumorphism: one tab of a strip - its ground, its edge, its lift and its shadow at rest, under the hand,
 * in use, lifted while the keys are on its strip, and in the hand. Whole: every property its look has (D9).
 */
public record Tab() implements CastingStyle<ColorScheme.Base, Neumorphism, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Tab> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.OnInverted(),
                new HouseColours.Border(), new HouseColours.Title(), new HouseColours.AccentWashFaint(),
                new HouseColours.AccentWashSoft(), new HouseColours.ShadeFirm());
    }
}
