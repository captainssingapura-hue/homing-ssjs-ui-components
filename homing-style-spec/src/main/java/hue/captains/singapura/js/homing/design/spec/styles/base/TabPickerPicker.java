package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** base's look of tab-picker_picker: CSS of its own. */
public record TabPickerPicker() implements CastingStylePerRole<BaseScheme, Base, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public StylingRes<BaseScheme, Base, HousePanes.TabPicker, HouseDoing.Picker> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HousePanes.TabPicker> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanes.TabPicker.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Prompt.INSTANCE, new KickerPrompt()),
                new PartSpec.Cast<>(HousePanes.TabPicker.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Choice.INSTANCE, new PlainButtonChoice()),
                new PartSpec.Cast<>(HousePanes.TabPicker.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Cancel.INSTANCE, new PlainButtonCancel())));
    }
}
