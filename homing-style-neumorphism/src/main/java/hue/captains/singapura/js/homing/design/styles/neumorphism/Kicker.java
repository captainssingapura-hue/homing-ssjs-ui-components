package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The kicker in neumorphism: the small line set above a title. Whole: every property its look has (D9). */
public record Kicker() implements CastingStyle<ColorScheme.Base, Neumorphism, HouseText.Kicker, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Kicker> style() {
        return new StylingRes.CssContent<>(new HouseColours.KickerInk());
    }
}
