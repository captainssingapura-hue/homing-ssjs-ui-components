package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** base's implementation of choice-widget: CSS of its own. */
public record ChoiceWidget() implements CastingStylePerRole<BaseScheme, Base, HousePreferences.ChoiceWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePreferences.ChoiceWidget, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
