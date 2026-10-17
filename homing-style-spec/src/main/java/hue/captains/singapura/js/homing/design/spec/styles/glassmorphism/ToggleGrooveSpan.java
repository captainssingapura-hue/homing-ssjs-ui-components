package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** glassmorphism's look of toggle-groove_span: as its component on its own, by delegation. */
public record ToggleGrooveSpan() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseMarks.ToggleGroove, HouseSaying.Span> style() {
        return new StylingRes.RootDelegation<>(new ToggleGroove());
    }
}
