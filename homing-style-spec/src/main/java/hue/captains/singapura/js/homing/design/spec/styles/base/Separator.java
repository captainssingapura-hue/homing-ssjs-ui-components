package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** base's look of separator: CSS of its own. */
public record Separator() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.Separator, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.Separator, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Hairline());
    }
}
