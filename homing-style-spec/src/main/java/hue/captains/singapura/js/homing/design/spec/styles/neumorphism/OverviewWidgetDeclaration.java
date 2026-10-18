package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neumorphism's declaration of overview-widget: its own implementation, and a spec for each of its slots. */
public record OverviewWidgetDeclaration() implements CastingDeclaration<BaseScheme, Neumorphism, HousePreferences.OverviewWidget, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HousePreferences.OverviewWidget, RoleRoot> own() { return new OverviewWidget(); }

    @Override public PartsSpec<BaseScheme, Neumorphism, HousePreferences.OverviewWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePreferences.OverviewWidget.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Category.INSTANCE, new Target.Impl<>(new KickerCategory())),
                new PartSpec.SameRole<>(HousePreferences.OverviewWidget.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new Heading4Title())),
                new PartSpec.SameRole<>(HousePreferences.OverviewWidget.INSTANCE, HouseText.Lede.INSTANCE, HouseSaying.Summary.INSTANCE, new Target.Impl<>(new LedeSummary())),
                new PartSpec.Standalone<>(HousePreferences.OverviewWidget.INSTANCE, HousePreferences.SettingRow.INSTANCE, HouseShaping.Entry.INSTANCE),
                new PartSpec.Standalone<>(HousePreferences.OverviewWidget.INSTANCE, HouseMarks.Separator.INSTANCE, HouseShaping.Break.INSTANCE),
                new PartSpec.SameRole<>(HousePreferences.OverviewWidget.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Reset.INSTANCE, new Target.Impl<>(new PlainButtonReset()))));
    }
}
