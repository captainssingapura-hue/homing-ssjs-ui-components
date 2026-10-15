package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The separator in neumorphism: a line between groups - its foot ruled in the colour it is given. Whole: every property
 * its look has (D9).
 */
public record Separator() implements CastingStyle<ColorScheme.Base, Neumorphism, HouseMarks.Separator, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Separator> style() {
        return new StylingRes.CssContent<>(new HouseColours.Hairline());
    }
}
