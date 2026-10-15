package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Base's switch toggle: as its switch on its own, by delegation. */
public record SwitchToggle() implements CastingStyle<ColorScheme.Base, Base, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Switch> style() {
        return new StylingRes.Delegation<>(new Switch());
    }
}
