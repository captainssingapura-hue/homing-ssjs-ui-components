package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-brutalism's implementation of slider-groove_span: as its component standalone, by delegation. */
public record SliderGrooveSpan() implements RootDelegation<BaseScheme, NeoBrutalism, HouseMarks.SliderGroove, HouseSaying.Span> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseMarks.SliderGroove, RoleRoot> to() { return new SliderGroove(); }
}
