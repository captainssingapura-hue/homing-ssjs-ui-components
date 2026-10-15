package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The checkbox in neumorphism: one thing on or off, its mark in the accent. Whole: every property its look has (D9).
 */
public record Checkbox() implements CastingStyle<ColorScheme.Base, Neumorphism, HouseControls.Checkbox, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Checkbox> style() {
        return new StylingRes.CssContent<>(new HouseColours.Accent(), new HouseColours.Ring());
    }
}
