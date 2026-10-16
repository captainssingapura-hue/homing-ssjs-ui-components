package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** The tab picker in the base layer: no look of its own - its choices draw; every style's alike. */
public record TabPicker() implements CastingStyle<BaseScheme, Base, HousePanes.TabPicker, RoleRoot> {
    @Override public StylingRes<BaseScheme, HousePanes.TabPicker> style() {
        return new StylingRes.CssContent<>();
    }
}
