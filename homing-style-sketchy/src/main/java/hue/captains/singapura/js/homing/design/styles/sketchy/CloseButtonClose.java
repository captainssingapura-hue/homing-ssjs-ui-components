package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's close button close: as its close button on its own, by delegation. */
public record CloseButtonClose()
        implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.CloseButton, HouseDoing.Close> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.CloseButton> style() {
        return new StylingRes.Delegation<>(new CloseButton());
    }
}
