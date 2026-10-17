package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Neumorphism's toggle groove span: as its toggle groove on its own, by delegation. */
public record ToggleGrooveSpan()
        implements CastingStyle<BaseScheme, Neumorphism, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public StylingRes<BaseScheme, HouseMarks.ToggleGroove> style() {
        return new StylingRes.Delegation<>(new ToggleGroove());
    }
}
