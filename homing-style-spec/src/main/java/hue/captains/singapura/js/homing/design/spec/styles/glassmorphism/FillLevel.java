package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** glassmorphism's look of fill_level: CSS of its own. */
public record FillLevel() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseMarks.Fill, HouseSaying.Level> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
