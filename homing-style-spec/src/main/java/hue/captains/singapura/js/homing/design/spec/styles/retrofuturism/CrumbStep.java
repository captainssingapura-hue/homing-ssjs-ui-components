package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** retro-futurism's look of crumb_step: as its component on its own, by delegation. */
public record CrumbStep() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.Crumb, HouseDoing.Step> style() {
        return new StylingRes.RootDelegation<>(new Crumb());
    }
}
