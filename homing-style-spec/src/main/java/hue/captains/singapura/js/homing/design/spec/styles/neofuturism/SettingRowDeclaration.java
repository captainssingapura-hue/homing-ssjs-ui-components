package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-futurism's declaration of setting-row: its own implementation, and a spec for each of its slots. */
public record SettingRowDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HousePreferences.SettingRow, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoFuturism, HousePreferences.SettingRow, RoleRoot> own() { return new SettingRow(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HousePreferences.SettingRow> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePreferences.SettingRow.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Value.INSTANCE, new Target.Impl<>(new CaptionValue()))));
    }
}
