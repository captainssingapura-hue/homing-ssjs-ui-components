package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/**
 * The pan-zoom bar in the base layer: no look of its own - its buttons and its readout draw, its row the module's
 * layout; every style's alike.
 */
public record PanZoomBar() implements CastingStyle<BaseScheme, Base, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, HousePanZoom.PanZoomBar> style() {
        return new StylingRes.CssContent<>();
    }
}
