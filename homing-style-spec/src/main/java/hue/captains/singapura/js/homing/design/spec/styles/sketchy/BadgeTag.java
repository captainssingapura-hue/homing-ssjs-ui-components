package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's look of badge_tag: as its component on its own, by delegation. */
public record BadgeTag() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Badge, HouseSaying.Tag> style() {
        return new StylingRes.RootDelegation<>(new Badge());
    }
}
