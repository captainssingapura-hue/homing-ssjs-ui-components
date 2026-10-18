package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** base's implementation of tab-pane: CSS of its own - its content apart, the colours it draws with typed here. */
public record TabPane() implements CastingStylePerRole<BaseScheme, Base, HousePanes.TabPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePanes.TabPane, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
