package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The code in glassmorphism: code in a line of words, in a fixed face on a faint wash. Whole: every property its look
 * has (D9).
 */
public record Code() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseText.Code, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Code> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashFaint());
    }
}
