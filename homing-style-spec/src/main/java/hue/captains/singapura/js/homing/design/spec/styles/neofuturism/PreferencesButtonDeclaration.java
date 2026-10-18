package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** neo-futurism's declaration of preferences-button: its own implementation, and a spec for each of its slots. */
public record PreferencesButtonDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseControls.PreferencesButton, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseControls.PreferencesButton, RoleRoot> own() { return new PreferencesButton(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseControls.PreferencesButton> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseControls.PreferencesButton.INSTANCE, HouseIcons.SettingsIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.PreferencesButton.INSTANCE, HouseContainers.Dialog.INSTANCE, HouseShaping.Window.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.PreferencesButton.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Page.INSTANCE)));
    }
}
