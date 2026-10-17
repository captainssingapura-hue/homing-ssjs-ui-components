package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** sketchy's implementation of ground: CSS of its own. */
public record Ground() implements CastingStylePerRole<BaseScheme, Sketchy, HouseContainers.Ground, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseContainers.Ground, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ground(), new BaseColours.ScrollThumb(), new BaseColours.Text());
    }
}
