package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neo-brutalism's implementation of slider-rack: CSS of its own. */
public record SliderRack() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseRegions.SliderRack, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseRegions.SliderRack, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
