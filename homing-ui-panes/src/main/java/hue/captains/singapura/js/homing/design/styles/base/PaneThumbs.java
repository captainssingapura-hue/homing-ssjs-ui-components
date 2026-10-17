package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** The pane thumbnails in the base layer: no look of its own - its thumbnails draw; every style's alike. */
public record PaneThumbs() implements CastingStyle<BaseScheme, Base, HousePanes.PaneThumbs, RoleRoot> {
    @Override public StylingRes<BaseScheme, HousePanes.PaneThumbs> style() {
        return new StylingRes.CssContent<>();
    }
}
