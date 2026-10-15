package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The crumb in the base layer: a step of a trail back, in the colour of what holds it; the page itself the boldest. */
public record Crumb() implements CastingStyle<ColorScheme.Base, Base, HouseControls.Crumb, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Crumb> style() {
        return new StylingRes.CssContent<>(new HouseColours.Ring());
    }
}
