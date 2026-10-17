package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of plain-button_reset: as its component standalone, by delegation. */
public record PlainButtonReset() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.PlainButton, HouseDoing.Reset> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.PlainButton, HouseDoing.Reset> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
