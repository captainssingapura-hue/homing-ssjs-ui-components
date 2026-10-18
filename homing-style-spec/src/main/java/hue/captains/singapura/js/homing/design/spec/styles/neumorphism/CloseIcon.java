package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** neumorphism's implementation of close-icon: CSS of its own - its content apart, the colours it draws with typed here. */
public record CloseIcon() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseIcons.CloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseIcons.CloseIcon, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
