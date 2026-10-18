package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.ParentDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** neo-futurism's implementation of tab-picker_picker: the base's, by delegation. */
public record TabPickerPicker() implements ParentDelegation<BaseScheme, Base, NeoFuturism, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.TabPicker, HouseDoing.Picker> to() { return new hue.captains.singapura.js.homing.design.spec.styles.base.TabPickerPicker(); }
}
