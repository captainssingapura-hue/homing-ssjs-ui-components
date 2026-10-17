package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's look of readout_value: as its component on its own, by delegation. */
public record ReadoutValue() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Readout, HouseSaying.Value> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Readout, HouseSaying.Value> style() {
        return new StylingRes.RootDelegation<>(new Readout());
    }
}
