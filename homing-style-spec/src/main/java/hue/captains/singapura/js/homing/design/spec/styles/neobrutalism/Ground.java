package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neo-brutalism's look of ground: CSS of its own. */
public record Ground() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.Ground, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.Ground, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ground(), new BaseColours.ScrollThumb(), new BaseColours.Text());
    }
}
