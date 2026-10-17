package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

import java.util.List;

/** editorial's declaration of list-master-widget: its own implementation, and a spec for each of its slots. */
public record ListMasterWidgetDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Editorial, HousePreferences.ListMasterWidget, RoleRoot> own() { return new ListMasterWidget(); }

    @Override public PartsSpec<BaseScheme, Editorial, HousePreferences.ListMasterWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePreferences.ListMasterWidget.INSTANCE, HouseItems.ListRow.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Impl<>(new ListRowChoice()))));
    }
}
