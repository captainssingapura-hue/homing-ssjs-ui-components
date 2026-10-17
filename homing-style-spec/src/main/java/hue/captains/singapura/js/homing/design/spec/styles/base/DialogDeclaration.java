package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of dialog: its own implementation, and a spec for each of its slots. */
public record DialogDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseContainers.Dialog, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HouseContainers.Dialog, RoleRoot> own() { return new Dialog(); }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.Dialog> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.Dialog.INSTANCE, HouseContainers.Scrim.INSTANCE, HouseShaping.Veil.INSTANCE),
                new PartSpec.Standalone<>(HouseContainers.Dialog.INSTANCE, HouseContainers.FloatingPane.INSTANCE, HouseShaping.Window.INSTANCE),
                new PartSpec.Standalone<>(HouseContainers.Dialog.INSTANCE, HouseContainers.ActionBar.INSTANCE, HouseShaping.Actions.INSTANCE)));
    }
}
