package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

import java.util.List;

/** retro-futurism's declaration of list-master-widget: its own implementation, and a spec for each of its slots. */
public record ListMasterWidgetDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, RetroFuturism, HousePreferences.ListMasterWidget, RoleRoot> own() { return new ListMasterWidget(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HousePreferences.ListMasterWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePreferences.ListMasterWidget.INSTANCE, HouseItems.ListRow.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Impl<>(new ListRowChoice()))));
    }
}
