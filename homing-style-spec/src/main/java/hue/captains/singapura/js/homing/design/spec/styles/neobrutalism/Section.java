package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neo-brutalism's look of section: CSS of its own. */
public record Section() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseRegions.Section, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseRegions.Section, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
