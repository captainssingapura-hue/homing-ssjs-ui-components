package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** RetroFuturism's preferences button preferences: as its preferences button on its own, by delegation. */
public record PreferencesButtonPreferences()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.PreferencesButton, HouseDoing.Preferences> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PreferencesButton> style() {
        return new StylingRes.Delegation<>(new PreferencesButton());
    }
}
