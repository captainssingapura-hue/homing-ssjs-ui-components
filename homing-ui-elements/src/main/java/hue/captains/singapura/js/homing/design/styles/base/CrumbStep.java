package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Base's crumb step: as its crumb on its own, by delegation. */
public record CrumbStep() implements CastingStyle<BaseScheme, Base, HouseControls.Crumb, HouseDoing.Step> {
    @Override public StylingRes<BaseScheme, HouseControls.Crumb> style() {
        return new StylingRes.Delegation<>(new Crumb());
    }
}
