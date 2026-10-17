package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** neo-futurism's implementation of toggle-widget: the base's, by delegation. */
public record ToggleWidget() implements CastingStylePerRole<BaseScheme, NeoFuturism, HousePreferences.ToggleWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HousePreferences.ToggleWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleWidget());
    }
}
