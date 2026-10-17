package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** editorial's implementation of range: CSS of its own. */
public record Range() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.Range, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.Range, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent(), new BaseColours.Ring());
    }
}
