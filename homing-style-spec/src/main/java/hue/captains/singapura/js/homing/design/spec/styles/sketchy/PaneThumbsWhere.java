package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** sketchy's implementation of pane-thumbs_where: the base's, by delegation. */
public record PaneThumbsWhere() implements ParentDelegation<BaseScheme, Base, Sketchy, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.PaneThumbs, HouseDoing.Where> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbsWhere(); }
}
