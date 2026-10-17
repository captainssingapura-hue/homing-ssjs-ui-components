package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neo-futurism's look of widget-slot: base's, by delegation. */
public record WidgetSlot() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseRegions.WidgetSlot, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseRegions.WidgetSlot, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.WidgetSlot());
    }
}
