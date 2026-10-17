package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The reset icon in the base layer: an arrow turning back, in the colour it is given; every style's alike unless one draws its own (D52). Whole: every property its look has (D9). */
public record ResetIcon() implements CastingStyle<BaseScheme, Base, HouseIcons.ResetIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.ResetIcon> style() {
        return new StylingRes.CssContent<>();
    }
}
