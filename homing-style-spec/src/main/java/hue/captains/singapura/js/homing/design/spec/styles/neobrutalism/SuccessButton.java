package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neo-brutalism's implementation of success-button: CSS of its own. */
public record SuccessButton() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.SuccessButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.SuccessButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Inverted(), new BaseColours.SuccessInk(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.SuccessGround());
    }
}
