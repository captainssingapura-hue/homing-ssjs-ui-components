package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** base's implementation of pane-thumbs_where: as its component standalone, by delegation. */
public record PaneThumbsWhere() implements RootDelegation<BaseScheme, Base, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.PaneThumbs, RoleRoot> to() { return new PaneThumbs(); }
}
