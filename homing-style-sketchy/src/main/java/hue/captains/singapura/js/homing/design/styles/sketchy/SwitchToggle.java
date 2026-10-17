package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's switch toggle: as its switch on its own, by delegation. */
public record SwitchToggle()
        implements CastingStyle<BaseScheme, Sketchy, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public StylingRes<BaseScheme, HouseControls.Switch> style() {
        return new StylingRes.Delegation<>(new Switch());
    }
}
