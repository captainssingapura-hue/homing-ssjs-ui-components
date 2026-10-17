package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Glassmorphism's link open: as its link on its own, by delegation. */
public record LinkOpen() implements CastingStyle<BaseScheme, Glassmorphism, HouseControls.Link, HouseDoing.Open> {
    @Override public StylingRes<BaseScheme, HouseControls.Link> style() {
        return new StylingRes.Delegation<>(new Link());
    }
}
