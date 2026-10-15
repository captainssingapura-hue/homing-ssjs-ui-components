package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** The widget slot in the base layer: no look of its own - what it holds draws; every style's alike. */
public record WidgetSlot() implements CastingStyle<ColorScheme.Base, Base, HouseRegions.WidgetSlot, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseRegions.WidgetSlot> style() {
        return new StylingRes.CssContent<>();
    }
}
