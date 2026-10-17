package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** retro-futurism's look of slider-rack: CSS of its own. */
public record SliderRack() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseRegions.SliderRack, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseRegions.SliderRack, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
