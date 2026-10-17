package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's look of primary-button_go: as its component on its own, by delegation. */
public record PrimaryButtonGo() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.PrimaryButton, HouseDoing.Go> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.PrimaryButton, HouseDoing.Go> style() {
        return new StylingRes.RootDelegation<>(new PrimaryButton());
    }
}
