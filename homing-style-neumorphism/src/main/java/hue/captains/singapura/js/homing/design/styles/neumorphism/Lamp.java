package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The lamp in neumorphism: a light that says how something is - quiet at rest, lit in the accent, broken in danger's
 * colour. Whole: every property its look has (D9).
 */
public record Lamp() implements CastingStyle<ColorScheme.Base, Neumorphism, HouseMarks.Lamp, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Lamp> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Text(), new HouseColours.OnDanger(), new HouseColours.Quiet(),
                new HouseColours.Accent(), new HouseColours.AccentWashLight(), new HouseColours.Danger());
    }
}
