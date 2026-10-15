package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's brand home: as its brand on its own, by delegation. */
public record BrandHome() implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Brand> style() {
        return new StylingRes.Delegation<>(new Brand());
    }
}
