package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's implementation of kicker_title: as its component standalone, by delegation. */
public record KickerTitle() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Kicker, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Kicker, HouseSaying.Title> style() {
        return new StylingRes.RootDelegation<>(new Kicker());
    }
}
