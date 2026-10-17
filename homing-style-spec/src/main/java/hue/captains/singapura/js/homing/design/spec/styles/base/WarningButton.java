package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** base's look of warning-button: CSS of its own. */
public record WarningButton() implements CastingStylePerRole<BaseScheme, Base, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.WarningButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.WarningInk(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.WarningGround());
    }
}
