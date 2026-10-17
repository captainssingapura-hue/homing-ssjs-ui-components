package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of caption_hint: CSS of its own. */
public record CaptionHint() implements CastingStylePerRole<BaseScheme, Base, HouseText.Caption, HouseSaying.Hint> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Caption, HouseSaying.Hint> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
