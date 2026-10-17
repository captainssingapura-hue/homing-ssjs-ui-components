package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's implementation of plain-button_fit: as its component standalone, by delegation. */
public record PlainButtonFit() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.PlainButton, HouseDoing.Fit> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.PlainButton, HouseDoing.Fit> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
