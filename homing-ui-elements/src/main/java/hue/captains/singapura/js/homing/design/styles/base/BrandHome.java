package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Base's brand home: as its brand on its own, by delegation. */
public record BrandHome() implements CastingStyle<ColorScheme.Base, Base, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Brand> style() {
        return new StylingRes.Delegation<>(new Brand());
    }
}
