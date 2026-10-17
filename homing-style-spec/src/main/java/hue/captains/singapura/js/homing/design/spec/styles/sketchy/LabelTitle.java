package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's implementation of label_title: CSS of its own. */
public record LabelTitle() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Label, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Label, HouseSaying.Title> style() {
        return new StylingRes.CssContent<>();
    }
}
