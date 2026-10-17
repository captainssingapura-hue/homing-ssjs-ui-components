package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import java.util.List;

/** editorial's look of list-master-widget: base's, by delegation. */
public record ListMasterWidget() implements CastingStylePerRole<BaseScheme, Editorial, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HousePreferences.ListMasterWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ListMasterWidget());
    }

    @Override public PartsSpec<BaseScheme, Editorial, HousePreferences.ListMasterWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.ListMasterWidget.INSTANCE, HouseItems.ListRow.INSTANCE, HouseDoing.Choice.INSTANCE, new ListRowChoice())));
    }
}
