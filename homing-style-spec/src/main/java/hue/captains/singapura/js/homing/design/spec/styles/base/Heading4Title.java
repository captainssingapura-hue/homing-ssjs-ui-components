package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's implementation of heading4_title: as its component standalone, by delegation. */
public record Heading4Title() implements CastingStylePerRole<BaseScheme, Base, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Heading4, HouseSaying.Title> style() {
        return new StylingRes.RootDelegation<>(new Heading4());
    }
}
