package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** editorial's implementation of tab-picker_picker: the base's, by delegation. */
public record TabPickerPicker() implements ParentDelegation<BaseScheme, Base, Editorial, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.TabPicker, HouseDoing.Picker> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.TabPickerPicker(); }
}
