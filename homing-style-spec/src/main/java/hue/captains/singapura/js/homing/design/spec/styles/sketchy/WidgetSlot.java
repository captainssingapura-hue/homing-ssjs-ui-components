package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** sketchy's look of widget-slot: base's, by delegation. */
public record WidgetSlot() implements CastingStylePerRole<BaseScheme, Sketchy, HouseRegions.WidgetSlot, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseRegions.WidgetSlot, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.WidgetSlot());
    }
}
