package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The lamp in glassmorphism: a light that says how something is - quiet at rest, lit in the accent, broken in danger's
 * colour. Whole: every property its look has (D9).
 */
public record Lamp() implements CastingStyle<BaseScheme, Glassmorphism, HouseMarks.Lamp, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Lamp> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Text(), new BaseColours.OnDanger(), new BaseColours.Quiet(),
                new BaseColours.Accent(), new BaseColours.AccentWashLight(), new BaseColours.Danger());
    }
}
