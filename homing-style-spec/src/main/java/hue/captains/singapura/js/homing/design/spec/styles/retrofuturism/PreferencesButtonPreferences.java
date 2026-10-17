package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** retro-futurism's look of preferences-button_preferences: as its component on its own, by delegation. */
public record PreferencesButtonPreferences() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.PreferencesButton, HouseDoing.Preferences> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.PreferencesButton, HouseDoing.Preferences> style() {
        return new StylingRes.RootDelegation<>(new PreferencesButton());
    }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HouseControls.PreferencesButton> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HouseControls.PreferencesButton.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Page.INSTANCE, new WidgetSlot())));
    }
}
