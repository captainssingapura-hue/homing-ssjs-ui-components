package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** base's implementation of delimiter: CSS of its own. */
public record Delimiter() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.Delimiter, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.Delimiter, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Muted());
    }
}
