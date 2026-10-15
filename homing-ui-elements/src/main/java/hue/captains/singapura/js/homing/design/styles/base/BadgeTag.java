package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Base's badge tag: as its badge on its own, by delegation. */
public record BadgeTag() implements CastingStyle<ColorScheme.Base, Base, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Badge> style() {
        return new StylingRes.Delegation<>(new Badge());
    }
}
