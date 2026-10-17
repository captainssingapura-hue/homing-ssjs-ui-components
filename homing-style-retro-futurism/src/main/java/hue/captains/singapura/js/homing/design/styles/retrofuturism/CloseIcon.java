package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The close icon in retro-futurism: its own picture, in the colour it is given (D52). Whole: every property its look has (D9). */
public record CloseIcon() implements CastingStyle<BaseScheme, RetroFuturism, HouseIcons.CloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.CloseIcon> style() {
        return new StylingRes.CssContent<>();
    }
}
