package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 6 in glassmorphism: a heading at the sixth level. Whole: every property its look has (D9). */
public record Heading6() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseText.Heading6, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading6> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
