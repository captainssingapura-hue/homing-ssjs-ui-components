package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** editorial's look of slider-rack: CSS of its own. */
public record SliderRack() implements CastingStylePerRole<BaseScheme, Editorial, HouseRegions.SliderRack, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseRegions.SliderRack, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
