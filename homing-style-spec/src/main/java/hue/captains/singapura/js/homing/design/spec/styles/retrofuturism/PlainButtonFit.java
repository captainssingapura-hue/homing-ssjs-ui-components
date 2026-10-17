package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** retro-futurism's look of plain-button_fit: as its component on its own, by delegation. */
public record PlainButtonFit() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.PlainButton, HouseDoing.Fit> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.PlainButton, HouseDoing.Fit> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
