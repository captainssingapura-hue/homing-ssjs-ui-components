package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** retro-futurism's implementation of setting-row: the base's, by delegation. */
public record SettingRow() implements ParentDelegation<BaseScheme, Base, RetroFuturism, HousePreferences.SettingRow, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.SettingRow, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.SettingRow(); }
}
