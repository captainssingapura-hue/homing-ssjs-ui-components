package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's implementation of brand_home: as its component standalone, by delegation. */
public record BrandHome() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.Brand, HouseDoing.Home> style() {
        return new StylingRes.RootDelegation<>(new Brand());
    }
}
