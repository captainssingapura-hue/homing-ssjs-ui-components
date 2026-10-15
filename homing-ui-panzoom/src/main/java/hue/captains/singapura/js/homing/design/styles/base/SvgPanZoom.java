package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/**
 * The picture that pans and zooms in the base layer: no look of its own - the drawing it holds draws; every style's
 * alike.
 */
public record SvgPanZoom() implements CastingStyle<ColorScheme.Base, Base, HousePanZoom.SvgPanZoom, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePanZoom.SvgPanZoom> style() {
        return new StylingRes.CssContent<>();
    }
}
