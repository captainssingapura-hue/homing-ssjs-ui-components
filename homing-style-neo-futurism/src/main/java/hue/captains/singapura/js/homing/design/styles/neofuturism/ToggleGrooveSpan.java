package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** NeoFuturism's toggle groove span: as its toggle groove on its own, by delegation. */
public record ToggleGrooveSpan()
        implements CastingStyle<BaseScheme, NeoFuturism, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public StylingRes<BaseScheme, HouseMarks.ToggleGroove> style() {
        return new StylingRes.Delegation<>(new ToggleGroove());
    }
}
