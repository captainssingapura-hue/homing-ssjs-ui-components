package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** sketchy's look of tab-picker_picker: base's, by delegation. */
public record TabPickerPicker() implements CastingStylePerRole<BaseScheme, Sketchy, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public StylingRes<BaseScheme, Sketchy, HousePanes.TabPicker, HouseDoing.Picker> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabPickerPicker());
    }

    @Override public PartsSpec<BaseScheme, Sketchy, HousePanes.TabPicker> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanes.TabPicker.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Prompt.INSTANCE, new KickerPrompt()),
                new PartSpec.Cast<>(HousePanes.TabPicker.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Choice.INSTANCE, new PlainButtonChoice()),
                new PartSpec.Cast<>(HousePanes.TabPicker.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Cancel.INSTANCE, new PlainButtonCancel())));
    }
}
