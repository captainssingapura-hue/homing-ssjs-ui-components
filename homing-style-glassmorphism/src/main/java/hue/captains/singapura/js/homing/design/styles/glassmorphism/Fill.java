package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The fill in glassmorphism: the part of the range the value has reached, in the accent. Whole: every property its look
 * has (D9).
 */
public record Fill() implements CastingStyle<BaseScheme, Glassmorphism, HouseMarks.Fill, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Fill> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
