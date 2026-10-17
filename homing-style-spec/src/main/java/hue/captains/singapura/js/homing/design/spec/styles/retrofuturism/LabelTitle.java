package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's implementation of label_title: CSS of its own. */
public record LabelTitle() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Label, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Label, HouseSaying.Title> style() {
        return new StylingRes.CssContent<>();
    }
}
