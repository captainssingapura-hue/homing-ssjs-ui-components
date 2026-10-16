package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The tick in neumorphism: a scale's tick - a short line in the colour it is given. Whole: every property its look has
 * (D9).
 */
public record Tick() implements CastingStyle<BaseScheme, Neumorphism, HouseMarks.Tick, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Tick> style() {
        return new StylingRes.CssContent<>(new HouseColours.Muted());
    }
}
