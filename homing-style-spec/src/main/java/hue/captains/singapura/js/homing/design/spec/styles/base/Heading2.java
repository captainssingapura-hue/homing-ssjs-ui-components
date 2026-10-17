package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's implementation of heading2: CSS of its own. */
public record Heading2() implements CastingStylePerRole<BaseScheme, Base, HouseText.Heading2, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Heading2, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
