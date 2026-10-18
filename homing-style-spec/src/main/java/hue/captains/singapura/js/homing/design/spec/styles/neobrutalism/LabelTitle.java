package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's implementation of label_title: CSS of its own - its content apart, the colours it draws with typed here. */
public record LabelTitle() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.Label, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.Label, HouseSaying.Title> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
