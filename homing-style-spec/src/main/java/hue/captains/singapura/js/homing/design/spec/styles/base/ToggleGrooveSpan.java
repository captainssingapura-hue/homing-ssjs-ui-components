package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** base's look of toggle-groove_span: as its component on its own, by delegation. */
public record ToggleGrooveSpan() implements CastingStylePerRole<BaseScheme, Base, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public StylingRes<BaseScheme, Base, HouseMarks.ToggleGroove, HouseSaying.Span> style() {
        return new StylingRes.RootDelegation<>(new ToggleGroove());
    }
}
