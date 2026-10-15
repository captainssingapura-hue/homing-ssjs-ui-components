package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/**
 * The theme widget in the base layer: no look of its own - a widget draws only components: its choices draw; every
 * style's alike.
 */
public record ThemeWidget() implements CastingStyle<ColorScheme.Base, Base, HouseSite.ThemeWidget, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseSite.ThemeWidget> style() {
        return new StylingRes.CssContent<>();
    }
}
