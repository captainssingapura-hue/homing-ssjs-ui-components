package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's implementation of readout: CSS of its own. */
public record Readout() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.Readout, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
