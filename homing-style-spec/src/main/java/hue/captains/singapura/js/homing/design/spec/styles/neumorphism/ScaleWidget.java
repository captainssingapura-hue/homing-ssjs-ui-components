package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neumorphism's look of scale-widget: base's, by delegation. */
public record ScaleWidget() implements CastingStylePerRole<BaseScheme, Neumorphism, HousePreferences.ScaleWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HousePreferences.ScaleWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ScaleWidget());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HousePreferences.ScaleWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.ScaleWidget.INSTANCE, HouseControls.Range.INSTANCE, HouseDoing.Adjust.INSTANCE, new RangeAdjust()),
                new PartSpec.Cast<>(HousePreferences.ScaleWidget.INSTANCE, HouseText.Readout.INSTANCE, HouseSaying.Value.INSTANCE, new ReadoutValue())));
    }
}
