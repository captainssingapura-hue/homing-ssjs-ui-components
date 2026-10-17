package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's preferences button preferences: as its preferences button on its own, by delegation. */
public record PreferencesButtonPreferences()
        implements CastingStyle<BaseScheme, Sketchy, HouseControls.PreferencesButton, HouseDoing.Preferences> {
    @Override public StylingRes<BaseScheme, HouseControls.PreferencesButton> style() {
        return new StylingRes.Delegation<>(new PreferencesButton());
    }
}
