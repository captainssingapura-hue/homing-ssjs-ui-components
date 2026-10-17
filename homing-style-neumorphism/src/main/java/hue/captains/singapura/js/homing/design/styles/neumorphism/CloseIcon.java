package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The close icon in neumorphism: its own picture, in the colour it is given (D52). Whole: every property its look has (D9). */
public record CloseIcon() implements CastingStyle<BaseScheme, Neumorphism, HouseIcons.CloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.CloseIcon> style() {
        return new StylingRes.CssContent<>();
    }
}
