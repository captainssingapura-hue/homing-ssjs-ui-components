package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Editorial's crumb step: as its crumb on its own, by delegation. */
public record CrumbStep() implements CastingStyle<ColorScheme.Base, Editorial, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Crumb> style() {
        return new StylingRes.Delegation<>(new Crumb());
    }
}
