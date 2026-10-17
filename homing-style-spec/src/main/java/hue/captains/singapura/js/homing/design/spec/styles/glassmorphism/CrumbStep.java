package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of crumb_step: as its component standalone, by delegation. */
public record CrumbStep() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.Crumb, HouseDoing.Step> style() {
        return new StylingRes.RootDelegation<>(new Crumb());
    }
}
