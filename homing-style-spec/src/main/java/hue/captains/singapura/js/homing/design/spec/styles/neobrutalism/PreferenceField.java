package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neo-brutalism's look of preference-field: base's, by delegation. */
public record PreferenceField() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HousePreferences.PreferenceField, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HousePreferences.PreferenceField, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PreferenceField());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HousePreferences.PreferenceField> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Category.INSTANCE, new KickerCategory()),
                new PartSpec.Cast<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Heading4Title()),
                new PartSpec.Cast<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Lede.INSTANCE, HouseSaying.Summary.INSTANCE, new LedeSummary()),
                new PartSpec.Cast<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new CaptionNote()),
                new PartSpec.Cast<>(HousePreferences.PreferenceField.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Reset.INSTANCE, new PlainButtonReset())));
    }
}
