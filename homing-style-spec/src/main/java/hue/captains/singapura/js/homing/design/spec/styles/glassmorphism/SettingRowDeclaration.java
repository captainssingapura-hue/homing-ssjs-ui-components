package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** glassmorphism's declaration of setting-row: its own implementation, and a spec for each of its slots. */
public record SettingRowDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HousePreferences.SettingRow, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HousePreferences.SettingRow, RoleRoot> own() { return new SettingRow(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HousePreferences.SettingRow> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePreferences.SettingRow.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Value.INSTANCE, new Target.Impl<>(new CaptionValue()))));
    }
}
