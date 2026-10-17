package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** editorial's implementation of tab-strip: CSS of its own. */
public record TabStrip() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.TabStrip, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseContainers.TabStrip, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Accent(), new BaseColours.AccentTintHint());
    }
}
