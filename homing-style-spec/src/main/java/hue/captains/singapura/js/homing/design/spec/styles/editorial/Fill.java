package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** editorial's implementation of fill: CSS of its own. */
public record Fill() implements CastingStylePerRole<BaseScheme, Editorial, HouseMarks.Fill, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseMarks.Fill, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
