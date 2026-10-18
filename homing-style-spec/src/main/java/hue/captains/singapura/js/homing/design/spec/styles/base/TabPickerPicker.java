package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** base's implementation of tab-picker_picker: as its component standalone, by delegation. */
public record TabPickerPicker() implements RootDelegation<BaseScheme, Base, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.TabPicker, RoleRoot> to() { return new TabPicker(); }
}
