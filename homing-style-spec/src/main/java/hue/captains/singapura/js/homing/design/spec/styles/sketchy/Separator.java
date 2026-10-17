package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** sketchy's implementation of separator: CSS of its own. */
public record Separator() implements CastingStylePerRole<BaseScheme, Sketchy, HouseMarks.Separator, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseMarks.Separator, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Hairline());
    }
}
