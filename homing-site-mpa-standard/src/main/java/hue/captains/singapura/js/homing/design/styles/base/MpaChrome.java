package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/** The site's chrome in the base layer: no look of its own - its bar and what it frames draw; every style's alike. */
public record MpaChrome() implements CastingStyle<ColorScheme.Base, Base, HouseSite.MpaChrome, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseSite.MpaChrome> style() {
        return new StylingRes.CssContent<>();
    }
}
