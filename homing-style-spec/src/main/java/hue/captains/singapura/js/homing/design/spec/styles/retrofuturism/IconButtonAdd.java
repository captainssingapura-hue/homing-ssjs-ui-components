package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** retro-futurism's look of icon-button_add: as its component on its own, by delegation. */
public record IconButtonAdd() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.IconButton, HouseDoing.Add> style() {
        return new StylingRes.RootDelegation<>(new IconButton());
    }
}
