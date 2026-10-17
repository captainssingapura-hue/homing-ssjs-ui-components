package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** base's look of preferences-view: CSS of its own. */
public record PreferencesView() implements CastingStylePerRole<BaseScheme, Base, HousePreferences.PreferencesView, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePreferences.PreferencesView, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HousePreferences.PreferencesView> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HousePreferences.PreferencesView.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Index.INSTANCE, new WidgetSlot()),
                new PartSpec.Placed<>(HousePreferences.PreferencesView.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Detail.INSTANCE, new WidgetSlot())));
    }
}
