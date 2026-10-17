package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** base's implementation of lip: CSS of its own. */
public record Lip() implements CastingStylePerRole<BaseScheme, Base, HouseControls.Lip, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.Lip, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised());
    }
}
