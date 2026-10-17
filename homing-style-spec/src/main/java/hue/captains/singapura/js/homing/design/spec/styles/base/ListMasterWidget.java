package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import java.util.List;

/** base's look of list-master-widget: CSS of its own. */
public record ListMasterWidget() implements CastingStylePerRole<BaseScheme, Base, HousePreferences.ListMasterWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePreferences.ListMasterWidget, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HousePreferences.ListMasterWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.ListMasterWidget.INSTANCE, HouseItems.ListRow.INSTANCE, HouseDoing.Choice.INSTANCE, new ListRowChoice())));
    }
}
