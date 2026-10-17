package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's look of heading4_title: as its component on its own, by delegation. */
public record Heading4Title() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Heading4, HouseSaying.Title> style() {
        return new StylingRes.RootDelegation<>(new Heading4());
    }
}
