package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's implementation of readout: CSS of its own. */
public record Readout() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Readout, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
