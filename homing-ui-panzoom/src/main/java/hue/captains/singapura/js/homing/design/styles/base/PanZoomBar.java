package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/**
 * The pan-zoom bar in the base layer: no look of its own - its buttons and its readout draw, its row the module's
 * layout; every style's alike.
 */
public record PanZoomBar() implements CastingStyle<ColorScheme.Base, Base, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePanZoom.PanZoomBar> style() {
        return new StylingRes.CssContent<>();
    }
}
