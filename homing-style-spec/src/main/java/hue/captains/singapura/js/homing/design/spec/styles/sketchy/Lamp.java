package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** sketchy's implementation of lamp: CSS of its own. */
public record Lamp() implements CastingStylePerRole<BaseScheme, Sketchy, HouseMarks.Lamp, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseMarks.Lamp, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Text(), new BaseColours.OnDanger(), new BaseColours.Quiet(), new BaseColours.Accent(), new BaseColours.AccentWashLight(), new BaseColours.Danger());
    }
}
