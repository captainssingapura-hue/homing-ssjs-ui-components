package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Sketchy's heading4 title: as its heading4 on its own, by delegation. */
public record Heading4Title()
        implements CastingStyle<BaseScheme, Sketchy, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, HouseText.Heading4> style() {
        return new StylingRes.Delegation<>(new Heading4());
    }
}
