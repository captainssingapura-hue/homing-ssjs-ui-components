package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMenus;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of context-menu-steward: its own implementation, and a spec for each of its slots. */
public record ContextMenuStewardDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseMenus.ContextMenuSteward, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseMenus.ContextMenuSteward, RoleRoot> own() { return new ContextMenuSteward(); }

    @Override public PartsSpec<BaseScheme, Base, HouseMenus.ContextMenuSteward> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseMenus.ContextMenuSteward.INSTANCE, HouseContainers.ContextMenu.INSTANCE, HouseShaping.Popup.INSTANCE),
                new PartSpec.Standalone<>(HouseMenus.ContextMenuSteward.INSTANCE, HouseContainers.ContextMenu.INSTANCE, HouseShaping.Specimen.INSTANCE)));
    }
}
