package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's look of caption_summary: CSS of its own. */
public record CaptionSummary() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Summary> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
