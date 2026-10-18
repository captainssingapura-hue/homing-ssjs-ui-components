package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's implementation of preferences-button_preferences: as its component standalone, by delegation. */
public record PreferencesButtonPreferences() implements RootDelegation<BaseScheme, NeoBrutalism, HouseControls.PreferencesButton, HouseDoing.Preferences> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseControls.PreferencesButton, RoleRoot> to() { return new PreferencesButton(); }
}
