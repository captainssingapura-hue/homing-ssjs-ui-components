package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of label_name: as its component on its own, by delegation. */
public record LabelName() implements CastingStylePerRole<BaseScheme, Base, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Label, HouseSaying.Name> style() {
        return new StylingRes.RootDelegation<>(new Label());
    }
}
