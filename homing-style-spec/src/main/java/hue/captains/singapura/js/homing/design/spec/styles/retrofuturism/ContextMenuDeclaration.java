package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** retro-futurism's declaration of context-menu: its own implementation, and a spec for each of its slots. */
public record ContextMenuDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HouseContainers.ContextMenu, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, RetroFuturism, HouseContainers.ContextMenu, RoleRoot> own() { return new ContextMenu(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HouseContainers.ContextMenu> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.ContextMenu.INSTANCE, HouseItems.MenuItem.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Declared<>(new MenuItemChoiceDeclaration())),
                new PartSpec.Standalone<>(HouseContainers.ContextMenu.INSTANCE, HouseMarks.Separator.INSTANCE, HouseShaping.Break.INSTANCE)));
    }
}
