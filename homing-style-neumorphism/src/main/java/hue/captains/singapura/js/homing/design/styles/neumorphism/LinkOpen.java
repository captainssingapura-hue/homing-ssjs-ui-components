package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Neumorphism's link open: as its link on its own, by delegation. */
public record LinkOpen() implements CastingStyle<BaseScheme, Neumorphism, HouseControls.Link, HouseDoing.Open> {
    @Override public StylingRes<BaseScheme, HouseControls.Link> style() {
        return new StylingRes.Delegation<>(new Link());
    }
}
