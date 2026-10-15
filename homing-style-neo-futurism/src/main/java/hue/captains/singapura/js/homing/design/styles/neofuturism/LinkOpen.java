package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoFuturism's link open: as its link on its own, by delegation. */
public record LinkOpen() implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseControls.Link, HouseDoing.Open> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Link> style() {
        return new StylingRes.Delegation<>(new Link());
    }
}
