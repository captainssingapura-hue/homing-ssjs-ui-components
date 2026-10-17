package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/** retro-futurism's implementation of pan-zoom-bar: the base's, by delegation. */
public record PanZoomBar() implements CastingStylePerRole<BaseScheme, RetroFuturism, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HousePanZoom.PanZoomBar, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBar());
    }
}
