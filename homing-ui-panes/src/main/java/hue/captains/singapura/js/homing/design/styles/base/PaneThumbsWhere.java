package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** Base's pane thumbs where: as its pane thumbs on its own, by delegation. */
public record PaneThumbsWhere()
        implements CastingStyle<BaseScheme, Base, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public StylingRes<BaseScheme, HousePanes.PaneThumbs> style() {
        return new StylingRes.Delegation<>(new PaneThumbs());
    }
}
