package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** RetroFuturism's link open: as its link on its own, by delegation. */
public record LinkOpen() implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.Link, HouseDoing.Open> {
    @Override public StylingRes<BaseScheme, HouseControls.Link> style() {
        return new StylingRes.Delegation<>(new Link());
    }
}
