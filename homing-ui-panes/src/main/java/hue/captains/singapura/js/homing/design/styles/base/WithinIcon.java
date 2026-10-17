package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The within icon in the base layer: a dot inside a ring, the mark that says where the keys are - the colour it is given at rest, the accent carried in by its degree, part of the way on a bar and whole in a tab; every style's alike unless one draws its own (D52). Whole: every property its look has (D9). */
public record WithinIcon() implements CastingStyle<BaseScheme, Base, HouseIcons.WithinIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.WithinIcon> style() {
        return new StylingRes.CssContent<>(new BaseColours.KeysMark());
    }
}
