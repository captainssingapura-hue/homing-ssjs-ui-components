package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-futurism's look of fill_level: CSS of its own. */
public record FillLevel() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseMarks.Fill, HouseSaying.Level> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
