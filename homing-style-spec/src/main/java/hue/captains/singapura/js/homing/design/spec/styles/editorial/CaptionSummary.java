package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's implementation of caption_summary: as its component standalone, by delegation. */
public record CaptionSummary() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Summary> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Summary> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
