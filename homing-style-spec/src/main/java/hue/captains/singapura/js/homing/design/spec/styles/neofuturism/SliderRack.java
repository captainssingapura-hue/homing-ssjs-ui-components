package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neo-futurism's implementation of slider-rack: CSS of its own - its content apart, the colours it draws with typed here. */
public record SliderRack() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseRegions.SliderRack, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseRegions.SliderRack, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
