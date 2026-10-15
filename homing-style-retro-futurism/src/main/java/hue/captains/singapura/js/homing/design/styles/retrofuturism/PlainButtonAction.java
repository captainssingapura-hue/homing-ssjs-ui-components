package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** RetroFuturism's plain button action: as its plain button on its own, by delegation. */
public record PlainButtonAction()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.PlainButton, HouseDoing.Action> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PlainButton> style() {
        return new StylingRes.Delegation<>(new PlainButton());
    }
}
