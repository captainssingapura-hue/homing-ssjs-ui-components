package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** base's declaration of scale-widget: its own implementation, and a spec for each of its slots. */
public record ScaleWidgetDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePreferences.ScaleWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePreferences.ScaleWidget, RoleRoot> own() { return new ScaleWidget(); }

    @Override public PartsSpec<BaseScheme, Base, HousePreferences.ScaleWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePreferences.ScaleWidget.INSTANCE, HousePreferences.PreferenceField.INSTANCE, HouseShaping.Setting.INSTANCE),
                new PartSpec.SameRole<>(HousePreferences.ScaleWidget.INSTANCE, HouseControls.Range.INSTANCE, HouseDoing.Adjust.INSTANCE, new Target.Impl<>(new RangeAdjust())),
                new PartSpec.SameRole<>(HousePreferences.ScaleWidget.INSTANCE, HouseText.Readout.INSTANCE, HouseSaying.Value.INSTANCE, new Target.Impl<>(new ReadoutValue()))));
    }
}
