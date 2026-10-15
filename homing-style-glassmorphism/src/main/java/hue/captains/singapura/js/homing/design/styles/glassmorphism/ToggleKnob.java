package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The toggle knob in glassmorphism: what shows which way it stands, sliding in its groove; its measure the switch's.
 * Whole: every property its look has (D9).
 */
public record ToggleKnob()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.ToggleKnob, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.ToggleKnob> style() {
        return new StylingRes.CssContent<>(new HouseColours.Raised());
    }
}
