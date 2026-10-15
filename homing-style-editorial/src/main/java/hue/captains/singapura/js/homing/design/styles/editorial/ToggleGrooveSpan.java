package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** Editorial's toggle groove span: as its toggle groove on its own, by delegation. */
public record ToggleGrooveSpan()
        implements CastingStyle<ColorScheme.Base, Editorial, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.ToggleGroove> style() {
        return new StylingRes.Delegation<>(new ToggleGroove());
    }
}
