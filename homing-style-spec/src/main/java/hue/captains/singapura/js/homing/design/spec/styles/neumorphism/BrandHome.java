package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's implementation of brand_home: as its component standalone, by delegation. */
public record BrandHome() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.Brand, HouseDoing.Home> style() {
        return new StylingRes.RootDelegation<>(new Brand());
    }
}
