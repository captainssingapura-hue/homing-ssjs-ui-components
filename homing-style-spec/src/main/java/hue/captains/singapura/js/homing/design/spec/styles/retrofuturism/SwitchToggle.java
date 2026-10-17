package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** retro-futurism's implementation of switch_toggle: as its component standalone, by delegation. */
public record SwitchToggle() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.Switch, HouseDoing.Toggle> style() {
        return new StylingRes.RootDelegation<>(new Switch());
    }
}
