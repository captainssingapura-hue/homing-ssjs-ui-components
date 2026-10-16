package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Editorial's label name: as its label on its own, by delegation. */
public record LabelName() implements CastingStyle<BaseScheme, Editorial, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, HouseText.Label> style() {
        return new StylingRes.Delegation<>(new Label());
    }
}
