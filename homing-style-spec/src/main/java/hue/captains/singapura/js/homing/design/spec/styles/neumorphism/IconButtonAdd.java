package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neumorphism's implementation of icon-button_add: as its component standalone, by delegation. */
public record IconButtonAdd() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.IconButton, HouseDoing.Add> style() {
        return new StylingRes.RootDelegation<>(new IconButton());
    }
}
