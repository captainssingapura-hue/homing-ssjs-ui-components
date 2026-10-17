package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The rotate icon in the base layer: an arrow turning on, in the colour it is given; every style's alike unless one draws its own (D52). Whole: every property its look has (D9). */
public record RotateIcon() implements CastingStyle<BaseScheme, Base, HouseIcons.RotateIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.RotateIcon> style() {
        return new StylingRes.CssContent<>();
    }
}
