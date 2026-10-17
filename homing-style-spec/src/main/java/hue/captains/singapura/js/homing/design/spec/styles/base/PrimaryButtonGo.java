package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's look of primary-button_go: as its component on its own, by delegation. */
public record PrimaryButtonGo() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PrimaryButton, HouseDoing.Go> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.PrimaryButton, HouseDoing.Go> style() {
        return new StylingRes.RootDelegation<>(new PrimaryButton());
    }
}
