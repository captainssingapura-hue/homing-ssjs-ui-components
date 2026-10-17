package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** sketchy's implementation of setting-row: the base's, by delegation. */
public record SettingRow() implements CastingStylePerRole<BaseScheme, Sketchy, HousePreferences.SettingRow, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HousePreferences.SettingRow, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SettingRow());
    }
}
