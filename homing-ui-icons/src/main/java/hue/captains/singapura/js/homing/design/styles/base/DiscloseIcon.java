package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The disclose icon in the base layer: a pointer to what is beneath, in the colour it is given; every style's alike unless one draws its own (D52). Whole: every property its look has (D9). */
public record DiscloseIcon() implements CastingStyle<BaseScheme, Base, HouseIcons.DiscloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.DiscloseIcon> style() {
        return new StylingRes.CssContent<>();
    }
}
