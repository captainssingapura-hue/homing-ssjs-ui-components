package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Neumorphism's plain button fit: as its plain button on its own, by delegation. */
public record PlainButtonFit()
        implements CastingStyle<BaseScheme, Neumorphism, HouseControls.PlainButton, HouseDoing.Fit> {
    @Override public StylingRes<BaseScheme, HouseControls.PlainButton> style() {
        return new StylingRes.Delegation<>(new PlainButton());
    }
}
