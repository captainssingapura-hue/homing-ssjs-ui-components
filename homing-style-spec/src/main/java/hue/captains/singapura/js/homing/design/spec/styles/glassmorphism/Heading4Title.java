package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of heading4_title: as its component standalone, by delegation. */
public record Heading4Title() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Heading4, HouseSaying.Title> style() {
        return new StylingRes.RootDelegation<>(new Heading4());
    }
}
