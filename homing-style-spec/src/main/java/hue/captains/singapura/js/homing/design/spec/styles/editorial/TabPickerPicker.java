package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** editorial's implementation of tab-picker_picker: the base's, by delegation. */
public record TabPickerPicker() implements CastingStylePerRole<BaseScheme, Editorial, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public StylingRes<BaseScheme, Editorial, HousePanes.TabPicker, HouseDoing.Picker> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabPickerPicker());
    }
}
