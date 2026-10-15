package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's crumb step: as its crumb on its own, by delegation. */
public record CrumbStep() implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Crumb> style() {
        return new StylingRes.Delegation<>(new Crumb());
    }
}
