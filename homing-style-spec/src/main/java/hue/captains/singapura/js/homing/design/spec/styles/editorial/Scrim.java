package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** editorial's implementation of scrim: CSS of its own - its content apart, the colours it draws with typed here. */
public record Scrim() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseContainers.Scrim, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
