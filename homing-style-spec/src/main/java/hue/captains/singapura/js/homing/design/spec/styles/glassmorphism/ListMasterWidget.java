package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** glassmorphism's implementation of list-master-widget: the base's, by delegation. */
public record ListMasterWidget() implements CastingStylePerRole<BaseScheme, Glassmorphism, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HousePreferences.ListMasterWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidget());
    }
}
