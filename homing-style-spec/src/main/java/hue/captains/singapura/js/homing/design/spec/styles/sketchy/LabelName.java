package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's look of label_name: CSS of its own. */
public record LabelName() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Label, HouseSaying.Name> style() {
        return new StylingRes.CssContent<>();
    }
}
