package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

/** base's implementation of split-grid-mirror: CSS of its own. */
public record SplitGridMirror() implements CastingStylePerRole<BaseScheme, Base, HouseSplitGrid.SplitGridMirror, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseSplitGrid.SplitGridMirror, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
