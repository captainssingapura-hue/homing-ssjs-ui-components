package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/**
 * The tab pane in the base layer: no look of its own - one open tab whole, its chip and its page each drawing; it has
 * no element of its own; every style's alike.
 */
public record TabPane() implements CastingStyle<ColorScheme.Base, Base, HousePanes.TabPane, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePanes.TabPane> style() {
        return new StylingRes.CssContent<>();
    }
}
