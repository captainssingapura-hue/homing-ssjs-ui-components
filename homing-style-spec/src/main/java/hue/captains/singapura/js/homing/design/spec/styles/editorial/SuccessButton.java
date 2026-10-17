package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** editorial's look of success-button: CSS of its own. */
public record SuccessButton() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.SuccessButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.SuccessButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.SuccessInk(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.ShadeFaint(), new BaseColours.ShadeLight(), new BaseColours.ShadeMedium(), new BaseColours.SuccessGround());
    }
}
