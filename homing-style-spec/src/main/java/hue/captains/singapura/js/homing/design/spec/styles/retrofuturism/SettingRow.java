package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** retro-futurism's look of setting-row: base's, by delegation. */
public record SettingRow() implements CastingStylePerRole<BaseScheme, RetroFuturism, HousePreferences.SettingRow, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HousePreferences.SettingRow, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SettingRow());
    }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HousePreferences.SettingRow> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.SettingRow.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Value.INSTANCE, new CaptionValue())));
    }
}
