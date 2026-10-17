package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** neo-brutalism's look of fill_level: CSS of its own. */
public record FillLevel() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseMarks.Fill, HouseSaying.Level> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseMarks.Fill, HouseSaying.Level> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
