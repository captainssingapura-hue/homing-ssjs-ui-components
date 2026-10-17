package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** base's look of pane-thumbs_where: CSS of its own. */
public record PaneThumbsWhere() implements CastingStylePerRole<BaseScheme, Base, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public StylingRes<BaseScheme, Base, HousePanes.PaneThumbs, HouseDoing.Where> style() {
        return new StylingRes.CssContent<>();
    }
}
