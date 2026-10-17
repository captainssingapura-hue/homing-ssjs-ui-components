package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's implementation of danger-badge: CSS of its own. */
public record DangerBadge() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.DangerBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.DangerBadge, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.DangerInk(), new BaseColours.DangerWash());
    }
}
