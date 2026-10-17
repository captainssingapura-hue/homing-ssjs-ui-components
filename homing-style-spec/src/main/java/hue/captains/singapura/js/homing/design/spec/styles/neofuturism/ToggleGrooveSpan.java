package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-futurism's look of toggle-groove_span: as its component on its own, by delegation. */
public record ToggleGrooveSpan() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseMarks.ToggleGroove, HouseSaying.Span> style() {
        return new StylingRes.RootDelegation<>(new ToggleGroove());
    }
}
