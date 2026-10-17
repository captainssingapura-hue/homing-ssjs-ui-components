package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's look of crumb_step: CSS of its own. */
public record CrumbStep() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.Crumb, HouseDoing.Step> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
