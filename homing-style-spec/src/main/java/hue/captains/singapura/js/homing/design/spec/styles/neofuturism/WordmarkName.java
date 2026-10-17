package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's look of wordmark_name: CSS of its own. */
public record WordmarkName() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Wordmark, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Wordmark, HouseSaying.Name> style() {
        return new StylingRes.CssContent<>();
    }
}
