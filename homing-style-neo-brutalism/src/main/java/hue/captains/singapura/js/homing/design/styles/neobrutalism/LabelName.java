package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** NeoBrutalism's label name: as its label on its own, by delegation. */
public record LabelName() implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Label> style() {
        return new StylingRes.Delegation<>(new Label());
    }
}
