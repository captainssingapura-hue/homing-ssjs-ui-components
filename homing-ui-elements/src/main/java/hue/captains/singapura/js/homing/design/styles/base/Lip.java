package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The lip in the base layer: the thin band at an edge the hand comes to - the strip it shows, its raised ground,
 * showing; every style's alike. Whole: every property its look has (D9).
 */
public record Lip() implements CastingStyle<ColorScheme.Base, Base, HouseControls.Lip, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Lip> style() {
        return new StylingRes.CssContent<>(new HouseColours.Raised());
    }
}
