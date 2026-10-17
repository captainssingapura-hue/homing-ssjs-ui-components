package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's implementation of label_name: as its component standalone, by delegation. */
public record LabelName() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Label, HouseSaying.Name> style() {
        return new StylingRes.RootDelegation<>(new Label());
    }
}
