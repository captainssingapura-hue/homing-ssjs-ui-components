package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/** neo-brutalism's implementation of pan-zoom-bar: the base's, by delegation. */
public record PanZoomBar() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HousePanZoom.PanZoomBar, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBar());
    }
}
