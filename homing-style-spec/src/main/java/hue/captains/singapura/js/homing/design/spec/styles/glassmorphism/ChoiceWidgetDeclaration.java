package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** glassmorphism's declaration of choice-widget: its own implementation, and a spec for each of its slots. */
public record ChoiceWidgetDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HousePreferences.ChoiceWidget, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Glassmorphism, HousePreferences.ChoiceWidget, RoleRoot> own() { return new ChoiceWidget(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HousePreferences.ChoiceWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePreferences.ChoiceWidget.INSTANCE, HousePreferences.PreferenceField.INSTANCE, HouseShaping.Setting.INSTANCE),
                new PartSpec.SameRole<>(HousePreferences.ChoiceWidget.INSTANCE, HouseItems.ChoiceOption.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Declared<>(new ChoiceOptionChoiceDeclaration()))));
    }
}
