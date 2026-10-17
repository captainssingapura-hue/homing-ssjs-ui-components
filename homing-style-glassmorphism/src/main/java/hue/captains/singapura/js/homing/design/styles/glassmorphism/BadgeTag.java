package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Glassmorphism's badge tag: as its badge on its own, by delegation. */
public record BadgeTag() implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, HouseText.Badge> style() {
        return new StylingRes.Delegation<>(new Badge());
    }
}
