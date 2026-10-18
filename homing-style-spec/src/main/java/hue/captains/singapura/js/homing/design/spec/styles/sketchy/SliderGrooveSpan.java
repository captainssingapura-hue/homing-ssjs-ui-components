package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** sketchy's implementation of slider-groove_span: as its component standalone, by delegation. */
public record SliderGrooveSpan() implements RootDelegation<BaseScheme, Sketchy, HouseMarks.SliderGroove, HouseSaying.Span> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HouseMarks.SliderGroove, RoleRoot> to() { return new SliderGroove(); }
}
