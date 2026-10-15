package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** RetroFuturism's select what: as its select on its own, by delegation. */
public record SelectWhat()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.Select, HouseDoing.What> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Select> style() {
        return new StylingRes.Delegation<>(new Select());
    }
}
