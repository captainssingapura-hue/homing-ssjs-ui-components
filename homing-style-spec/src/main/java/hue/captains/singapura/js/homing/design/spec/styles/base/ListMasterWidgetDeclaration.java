package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

import java.util.List;

/** base's declaration of list-master-widget: its own implementation, and a spec for each of its slots. */
public record ListMasterWidgetDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ListMasterWidget, RoleRoot> own() { return new ListMasterWidget(); }

    @Override public PartsSpec<BaseScheme, Base, HousePreferences.ListMasterWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePreferences.ListMasterWidget.INSTANCE, HouseItems.ListRow.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Impl<>(new ListRowChoice()))));
    }
}
