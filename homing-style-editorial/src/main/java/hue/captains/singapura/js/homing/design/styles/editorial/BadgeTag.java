package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Editorial's badge tag: as its badge on its own, by delegation. */
public record BadgeTag() implements CastingStyle<ColorScheme.Base, Editorial, HouseText.Badge, HouseSaying.Tag> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Badge> style() {
        return new StylingRes.Delegation<>(new Badge());
    }
}
