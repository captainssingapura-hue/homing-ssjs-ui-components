package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The lamp in sketchy: a light that says how something is - quiet at rest, lit in the accent, broken in danger's
 * colour. Whole: every property its look has (D9).
 */
public record Lamp() implements CastingStyle<BaseScheme, Sketchy, HouseMarks.Lamp, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Lamp> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Text(), new BaseColours.OnDanger(), new BaseColours.Quiet(),
                new BaseColours.Accent(), new BaseColours.AccentWashLight(), new BaseColours.Danger());
    }
}
