package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/**
 * The toggle widget in the base layer: no look of its own - a widget draws only components: its switch draws; every
 * style's alike.
 */
public record ToggleWidget() implements CastingStyle<BaseScheme, Base, HousePreferences.ToggleWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, HousePreferences.ToggleWidget> style() {
        return new StylingRes.CssContent<>();
    }
}
