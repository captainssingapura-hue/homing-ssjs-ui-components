package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoBrutalism's brand home: as its brand on its own, by delegation. */
public record BrandHome()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<BaseScheme, HouseControls.Brand> style() {
        return new StylingRes.Delegation<>(new Brand());
    }
}
