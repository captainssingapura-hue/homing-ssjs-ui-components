package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

import java.util.List;

/** base's declaration of preferences-view: its own implementation, and a spec for each of its slots. */
public record PreferencesViewDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePreferences.PreferencesView, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HousePreferences.PreferencesView, RoleRoot> own() { return new PreferencesView(); }

    @Override public PartsSpec<BaseScheme, Base, HousePreferences.PreferencesView> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePreferences.PreferencesView.INSTANCE, HouseSplitGrid.SplitGrid.INSTANCE, HouseShaping.Layout.INSTANCE),
                new PartSpec.Standalone<>(HousePreferences.PreferencesView.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Index.INSTANCE),
                new PartSpec.Standalone<>(HousePreferences.PreferencesView.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Detail.INSTANCE)));
    }
}
