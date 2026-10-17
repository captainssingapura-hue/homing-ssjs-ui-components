package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** retro-futurism's implementation of list-master-widget: the base's, by delegation. */
public record ListMasterWidget() implements CastingStylePerRole<BaseScheme, RetroFuturism, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HousePreferences.ListMasterWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidget());
    }
}
