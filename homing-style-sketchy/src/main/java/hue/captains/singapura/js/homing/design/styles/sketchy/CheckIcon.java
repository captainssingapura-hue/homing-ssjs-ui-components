package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** The check icon in sketchy: its own picture, in the colour it is given (D52). Whole: every property its look has (D9). */
public record CheckIcon() implements CastingStyle<BaseScheme, Sketchy, HouseIcons.CheckIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseIcons.CheckIcon> style() {
        return new StylingRes.CssContent<>();
    }
}
