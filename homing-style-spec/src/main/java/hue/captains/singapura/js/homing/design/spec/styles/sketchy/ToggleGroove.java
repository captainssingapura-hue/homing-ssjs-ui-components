package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** sketchy's implementation of toggle-groove: CSS of its own. */
public record ToggleGroove() implements CastingStylePerRole<BaseScheme, Sketchy, HouseMarks.ToggleGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseMarks.ToggleGroove, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashSoft(), new BaseColours.GrooveEdge(), new BaseColours.Accent());
    }
}
