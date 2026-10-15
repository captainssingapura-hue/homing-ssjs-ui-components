package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The tick in glassmorphism: a scale's tick - a short line in the colour it is given. Whole: every property its look
 * has (D9).
 */
public record Tick() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseMarks.Tick, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Tick> style() {
        return new StylingRes.CssContent<>(new HouseColours.Muted());
    }
}
