package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The crumb in editorial: a step of a trail back, in the colour of what holds it; the page itself the boldest. */
public record Crumb() implements CastingStyle<BaseScheme, Editorial, HouseControls.Crumb, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Crumb> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
