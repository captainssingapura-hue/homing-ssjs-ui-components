package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's look of plain-button_choice: as its component on its own, by delegation. */
public record PlainButtonChoice() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.PlainButton, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.PlainButton, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
