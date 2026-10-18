package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** base's implementation of boundary-line_splitter: as its component standalone, by delegation. */
public record BoundaryLineSplitter() implements RootDelegation<BaseScheme, Base, HouseMarks.BoundaryLine, HouseDoing.Splitter> {
    @Override public CastingImplementation<BaseScheme, Base, HouseMarks.BoundaryLine, RoleRoot> to() { return new BoundaryLine(); }
}
