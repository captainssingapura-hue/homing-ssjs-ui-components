package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-futurism's implementation of select_how: as its component standalone, by delegation. */
public record SelectHow() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.Select, HouseDoing.How> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.Select, HouseDoing.How> style() {
        return new StylingRes.RootDelegation<>(new Select());
    }
}
