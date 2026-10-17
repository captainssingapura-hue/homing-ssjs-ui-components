package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Editorial's caption hint: as its caption on its own, by delegation. */
public record CaptionHint() implements CastingStyle<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Hint> {
    @Override public StylingRes<BaseScheme, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
