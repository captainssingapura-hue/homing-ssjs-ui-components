package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's implementation of plain-button_action: as its component standalone, by delegation. */
public record PlainButtonAction() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.PlainButton, HouseDoing.Action> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.PlainButton, HouseDoing.Action> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
