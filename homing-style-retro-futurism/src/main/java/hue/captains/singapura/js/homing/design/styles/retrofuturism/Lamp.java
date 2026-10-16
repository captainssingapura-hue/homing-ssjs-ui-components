package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The lamp in retro-futurism: a light that says how something is - quiet at rest, lit in the accent, broken in danger's
 * colour. Whole: every property its look has (D9).
 */
public record Lamp() implements CastingStyle<BaseScheme, RetroFuturism, HouseMarks.Lamp, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Lamp> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Text(), new HouseColours.OnDanger(), new HouseColours.Quiet(),
                new HouseColours.Accent(), new HouseColours.AccentWashLight(), new HouseColours.Danger());
    }
}
