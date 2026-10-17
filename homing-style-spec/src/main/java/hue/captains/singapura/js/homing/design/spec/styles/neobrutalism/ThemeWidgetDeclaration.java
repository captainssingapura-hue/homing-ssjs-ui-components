package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-brutalism's declaration of theme-widget: its own implementation, and a spec for each of its slots. */
public record ThemeWidgetDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseSite.ThemeWidget, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoBrutalism, HouseSite.ThemeWidget, RoleRoot> own() { return new ThemeWidget(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseSite.ThemeWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseSite.ThemeWidget.INSTANCE, HousePreferences.PreferenceField.INSTANCE, HouseShaping.Setting.INSTANCE),
                new PartSpec.SameRole<>(HouseSite.ThemeWidget.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Status.INSTANCE, new Target.Impl<>(new KickerStatus())),
                new PartSpec.SameRole<>(HouseSite.ThemeWidget.INSTANCE, HouseItems.ChoiceOption.INSTANCE, HouseDoing.Style.INSTANCE, new Target.Declared<>(new ChoiceOptionStyleDeclaration())),
                new PartSpec.SameRole<>(HouseSite.ThemeWidget.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Subtitle.INSTANCE, new Target.Impl<>(new KickerSubtitle())),
                new PartSpec.SameRole<>(HouseSite.ThemeWidget.INSTANCE, HouseItems.ChoiceOption.INSTANCE, HouseDoing.Palette.INSTANCE, new Target.Declared<>(new ChoiceOptionPaletteDeclaration()))));
    }
}
