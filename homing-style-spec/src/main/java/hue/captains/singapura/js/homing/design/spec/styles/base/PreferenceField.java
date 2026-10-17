package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** base's implementation of preference-field: CSS of its own. */
public record PreferenceField() implements CastingStylePerRole<BaseScheme, Base, HousePreferences.PreferenceField, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePreferences.PreferenceField, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
