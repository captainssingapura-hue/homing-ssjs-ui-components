package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's implementation of text-cell: CSS of its own. */
public record TextCell() implements CastingStylePerRole<BaseScheme, Base, HouseText.TextCell, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseText.TextCell, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Text());
    }
}
