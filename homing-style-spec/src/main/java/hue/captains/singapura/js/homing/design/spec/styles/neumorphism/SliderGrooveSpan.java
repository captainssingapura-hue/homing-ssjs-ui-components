package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neumorphism's implementation of slider-groove_span: as its component standalone, by delegation. */
public record SliderGrooveSpan() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseMarks.SliderGroove, HouseSaying.Span> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseMarks.SliderGroove, HouseSaying.Span> style() {
        return new StylingRes.RootDelegation<>(new SliderGroove());
    }
}
