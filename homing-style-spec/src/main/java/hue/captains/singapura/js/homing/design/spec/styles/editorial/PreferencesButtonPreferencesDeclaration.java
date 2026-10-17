package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** editorial's declaration of preferences-button_preferences: its own implementation, and a spec for each of its slots. */
public record PreferencesButtonPreferencesDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HouseControls.PreferencesButton, HouseDoing.Preferences> {
    @Override public CastingStylePerRole<BaseScheme, Editorial, HouseControls.PreferencesButton, HouseDoing.Preferences> own() { return new PreferencesButtonPreferences(); }

    @Override public PartsSpec<BaseScheme, Editorial, HouseControls.PreferencesButton> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseControls.PreferencesButton.INSTANCE, HouseIcons.SettingsIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.PreferencesButton.INSTANCE, HouseContainers.Dialog.INSTANCE, HouseShaping.Window.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.PreferencesButton.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Page.INSTANCE)));
    }
}
