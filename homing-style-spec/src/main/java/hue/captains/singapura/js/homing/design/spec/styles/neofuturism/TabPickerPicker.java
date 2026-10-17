package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** neo-futurism's implementation of tab-picker_picker: the base's, by delegation. */
public record TabPickerPicker() implements CastingStylePerRole<BaseScheme, NeoFuturism, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HousePanes.TabPicker, HouseDoing.Picker> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabPickerPicker());
    }
}
