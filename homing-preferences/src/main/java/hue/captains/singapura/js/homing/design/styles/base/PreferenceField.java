package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/**
 * The preference's field in the base layer: no look of its own - its label, its control and its note draw; every
 * style's alike.
 */
public record PreferenceField()
        implements CastingStyle<BaseScheme, Base, HousePreferences.PreferenceField, RoleRoot> {
    @Override public StylingRes<BaseScheme, HousePreferences.PreferenceField> style() {
        return new StylingRes.CssContent<>();
    }
}
