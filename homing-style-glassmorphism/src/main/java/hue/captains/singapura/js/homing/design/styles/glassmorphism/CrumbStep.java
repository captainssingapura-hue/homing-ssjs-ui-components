package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Glassmorphism's crumb step: as its crumb on its own, by delegation. */
public record CrumbStep()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<BaseScheme, HouseControls.Crumb> style() {
        return new StylingRes.Delegation<>(new Crumb());
    }
}
