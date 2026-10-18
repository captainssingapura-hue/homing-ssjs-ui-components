package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

/** sketchy's implementation of pan-zoom-bar: the base's, by delegation. */
public record PanZoomBar() implements ParentDelegation<BaseScheme, Base, Sketchy, HousePanZoom.PanZoomBar, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanZoom.PanZoomBar, RoleRoot> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBar(); }
}
