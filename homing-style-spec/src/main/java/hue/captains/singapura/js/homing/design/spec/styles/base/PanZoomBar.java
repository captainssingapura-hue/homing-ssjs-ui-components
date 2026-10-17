package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/** base's implementation of pan-zoom-bar: CSS of its own. */
public record PanZoomBar() implements CastingStylePerRole<BaseScheme, Base, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePanZoom.PanZoomBar, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
