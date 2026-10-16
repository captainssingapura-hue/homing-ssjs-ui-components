package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The grip in neumorphism: what the user takes hold of to move the thing it is on. Whole: every property its look has
 * (D9).
 */
public record Grip() implements CastingStyle<BaseScheme, Neumorphism, HouseControls.Grip, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Grip> style() {
        return new StylingRes.CssContent<>(new HouseColours.Ring());
    }
}
