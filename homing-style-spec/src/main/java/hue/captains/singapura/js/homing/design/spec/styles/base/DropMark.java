package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** base's implementation of drop-mark: CSS of its own. */
public record DropMark() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.DropMark, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.DropMark, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
