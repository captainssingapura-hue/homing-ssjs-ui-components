package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Glassmorphism's brand home: as its brand on its own, by delegation. */
public record BrandHome()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<BaseScheme, HouseControls.Brand> style() {
        return new StylingRes.Delegation<>(new Brand());
    }
}
