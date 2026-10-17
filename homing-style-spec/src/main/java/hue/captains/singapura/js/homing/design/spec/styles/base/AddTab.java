package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** base's implementation of add-tab: CSS of its own. */
public record AddTab() implements CastingStylePerRole<BaseScheme, Base, HousePanes.AddTab, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePanes.AddTab, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
