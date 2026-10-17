package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of heading4_title: as its component standalone, by delegation. */
public record Heading4Title() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Heading4, HouseSaying.Title> style() {
        return new StylingRes.RootDelegation<>(new Heading4());
    }
}
