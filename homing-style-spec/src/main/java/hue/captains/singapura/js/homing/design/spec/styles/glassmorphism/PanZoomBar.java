package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/** glassmorphism's implementation of pan-zoom-bar: the base's, by delegation. */
public record PanZoomBar() implements CastingStylePerRole<BaseScheme, Glassmorphism, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HousePanZoom.PanZoomBar, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBar());
    }
}
