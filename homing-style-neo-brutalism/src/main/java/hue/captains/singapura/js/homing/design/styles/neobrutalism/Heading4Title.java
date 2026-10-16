package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoBrutalism's heading4 title: as its heading4 on its own, by delegation. */
public record Heading4Title()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, HouseText.Heading4> style() {
        return new StylingRes.Delegation<>(new Heading4());
    }
}
