package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** RetroFuturism's brand home: as its brand on its own, by delegation. */
public record BrandHome()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Brand> style() {
        return new StylingRes.Delegation<>(new Brand());
    }
}
