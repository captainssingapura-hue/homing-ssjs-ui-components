package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.panes.HousePanes;
import hue.captains.singapura.js.homing.ui.styles.Base;

/** The tab opener in the base layer: no look of its own - its choices draw; every style's alike. */
public record TabOpener() implements CastingStyle<ColorScheme.Base, Base, HousePanes.TabOpener, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePanes.TabOpener> style() {
        return new StylingRes.CssContent<>();
    }
}
