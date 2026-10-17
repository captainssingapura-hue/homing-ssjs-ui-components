package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's look of kicker_name: as its component on its own, by delegation. */
public record KickerName() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Kicker, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Kicker, HouseSaying.Name> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
