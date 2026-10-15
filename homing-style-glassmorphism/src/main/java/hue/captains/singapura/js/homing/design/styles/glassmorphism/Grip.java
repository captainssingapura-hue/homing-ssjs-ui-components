package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The grip in glassmorphism: what the user takes hold of to move the thing it is on. Whole: every property its look has
 * (D9).
 */
public record Grip() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.Grip, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Grip> style() {
        return new StylingRes.CssContent<>(new HouseColours.Ring());
    }
}
