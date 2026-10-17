package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's look of kicker_title: CSS of its own. */
public record KickerTitle() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Kicker, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Kicker, HouseSaying.Title> style() {
        return new StylingRes.CssContent<>(new BaseColours.KickerInk());
    }
}
