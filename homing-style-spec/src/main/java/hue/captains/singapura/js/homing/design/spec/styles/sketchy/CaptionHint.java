package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's look of caption_hint: CSS of its own. */
public record CaptionHint() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Caption, HouseSaying.Hint> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Caption, HouseSaying.Hint> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
