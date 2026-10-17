package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's look of badge_tag: as its component on its own, by delegation. */
public record BadgeTag() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Badge, HouseSaying.Tag> style() {
        return new StylingRes.RootDelegation<>(new Badge());
    }
}
