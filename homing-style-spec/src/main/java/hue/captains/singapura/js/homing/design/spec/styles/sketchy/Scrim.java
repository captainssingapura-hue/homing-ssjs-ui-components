package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** sketchy's implementation of scrim: CSS of its own - its content apart, the colours it draws with typed here. */
public record Scrim() implements CastingStylePerRole<BaseScheme, Sketchy, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseContainers.Scrim, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
