package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's look of caption_value: CSS of its own. */
public record CaptionValue() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Value> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
