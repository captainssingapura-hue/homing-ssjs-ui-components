package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's look of crumb_step: CSS of its own. */
public record CrumbStep() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.Crumb, HouseDoing.Step> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
