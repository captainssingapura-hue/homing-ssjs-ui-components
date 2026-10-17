package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoFuturism's brand home: as its brand on its own, by delegation. */
public record BrandHome() implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<BaseScheme, HouseControls.Brand> style() {
        return new StylingRes.Delegation<>(new Brand());
    }
}
