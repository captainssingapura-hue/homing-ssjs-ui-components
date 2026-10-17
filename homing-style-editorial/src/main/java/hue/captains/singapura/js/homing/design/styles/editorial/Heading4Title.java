package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Editorial's heading4 title: as its heading4 on its own, by delegation. */
public record Heading4Title()
        implements CastingStyle<BaseScheme, Editorial, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, HouseText.Heading4> style() {
        return new StylingRes.Delegation<>(new Heading4());
    }
}
