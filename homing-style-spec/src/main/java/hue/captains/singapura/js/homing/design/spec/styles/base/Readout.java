package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of readout: CSS of its own. */
public record Readout() implements CastingStylePerRole<BaseScheme, Base, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Readout, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
