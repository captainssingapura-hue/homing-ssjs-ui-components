package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** sketchy's implementation of crumb: CSS of its own. */
public record Crumb() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.Crumb, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.Crumb, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
