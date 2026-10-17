package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Neumorphism's badge tag: as its badge on its own, by delegation. */
public record BadgeTag() implements CastingStyle<BaseScheme, Neumorphism, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, HouseText.Badge> style() {
        return new StylingRes.Delegation<>(new Badge());
    }
}
