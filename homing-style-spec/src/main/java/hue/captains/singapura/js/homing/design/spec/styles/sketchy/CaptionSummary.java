package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's look of caption_summary: as its component on its own, by delegation. */
public record CaptionSummary() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Caption, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Caption, HouseSaying.Summary> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
