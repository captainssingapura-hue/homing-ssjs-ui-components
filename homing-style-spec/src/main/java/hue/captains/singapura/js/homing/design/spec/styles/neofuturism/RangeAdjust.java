package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's look of range_adjust: as its component on its own, by delegation. */
public record RangeAdjust() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.Range, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.Range, HouseDoing.Adjust> style() {
        return new StylingRes.RootDelegation<>(new Range());
    }
}
