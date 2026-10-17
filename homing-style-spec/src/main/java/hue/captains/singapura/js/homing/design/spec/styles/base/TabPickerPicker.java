package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** base's implementation of tab-picker_picker: as its component standalone, by delegation. */
public record TabPickerPicker() implements CastingStylePerRole<BaseScheme, Base, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public StylingRes<BaseScheme, Base, HousePanes.TabPicker, HouseDoing.Picker> style() {
        return new StylingRes.RootDelegation<>(new TabPicker());
    }
}
