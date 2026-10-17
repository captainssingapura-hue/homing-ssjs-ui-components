package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's implementation of kicker_name: as its component standalone, by delegation. */
public record KickerName() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Kicker, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Kicker, HouseSaying.Name> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
