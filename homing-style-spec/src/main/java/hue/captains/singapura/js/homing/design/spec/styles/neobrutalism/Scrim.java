package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neo-brutalism's implementation of scrim: CSS of its own. */
public record Scrim() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.Scrim, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
