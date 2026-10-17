package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's look of label_name: CSS of its own. */
public record LabelName() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Label, HouseSaying.Name> style() {
        return new StylingRes.CssContent<>();
    }
}
