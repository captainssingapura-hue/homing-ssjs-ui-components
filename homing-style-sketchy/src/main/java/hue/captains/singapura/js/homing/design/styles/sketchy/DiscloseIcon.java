package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The disclose icon in sketchy: its own picture, in the colour it is given (D52). Whole: every property its look has (D9). */
public record DiscloseIcon() implements CastingStyle<BaseScheme, Sketchy, HouseIcons.DiscloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.DiscloseIcon> style() {
        return new StylingRes.CssContent<>();
    }
}
