package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Sketchy's badge tag: as its badge on its own, by delegation. */
public record BadgeTag() implements CastingStyle<BaseScheme, Sketchy, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<BaseScheme, HouseText.Badge> style() {
        return new StylingRes.Delegation<>(new Badge());
    }
}
