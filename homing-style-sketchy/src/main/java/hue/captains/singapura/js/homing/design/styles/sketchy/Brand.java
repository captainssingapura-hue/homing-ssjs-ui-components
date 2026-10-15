package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The brand in sketchy: the site's own, its mark beside its name, in the colour of what holds it - the way home. */
public record Brand() implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.Brand, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Brand> style() {
        return new StylingRes.CssContent<>(new HouseColours.Ring());
    }
}
