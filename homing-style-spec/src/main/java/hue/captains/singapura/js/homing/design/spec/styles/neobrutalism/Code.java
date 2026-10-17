package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's look of code: CSS of its own. */
public record Code() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.Code, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.Code, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashFaint());
    }
}
