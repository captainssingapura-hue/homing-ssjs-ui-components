package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's look of badge_tag: CSS of its own. */
public record BadgeTag() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Badge, HouseSaying.Tag> style() {
        return new StylingRes.CssContent<>(new BaseColours.AccentWash());
    }
}
