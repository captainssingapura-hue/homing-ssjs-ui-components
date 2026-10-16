package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Base's label name: as its label on its own, by delegation. */
public record LabelName() implements CastingStyle<BaseScheme, Base, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, HouseText.Label> style() {
        return new StylingRes.Delegation<>(new Label());
    }
}
