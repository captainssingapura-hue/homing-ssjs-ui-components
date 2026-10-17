package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** editorial's implementation of setting-row: the base's, by delegation. */
public record SettingRow() implements CastingStylePerRole<BaseScheme, Editorial, HousePreferences.SettingRow, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HousePreferences.SettingRow, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SettingRow());
    }
}
