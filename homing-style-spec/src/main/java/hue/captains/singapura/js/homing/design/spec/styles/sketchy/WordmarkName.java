package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's implementation of wordmark_name: as its component standalone, by delegation. */
public record WordmarkName() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Wordmark, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Wordmark, HouseSaying.Name> style() {
        return new StylingRes.RootDelegation<>(new Wordmark());
    }
}
