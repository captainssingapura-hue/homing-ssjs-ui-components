package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** base's implementation of slider-rack: CSS of its own - its content apart, the colours it draws with typed here. */
public record SliderRack() implements CssImplementation<BaseScheme, Base, HouseRegions.SliderRack, RoleRoot> {}
