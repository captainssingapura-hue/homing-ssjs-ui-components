package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** Base's tab picker picker: as its tab picker on its own, by delegation. */
public record TabPickerPicker()
        implements CastingStyle<ColorScheme.Base, Base, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public StylingRes<ColorScheme.Base, HousePanes.TabPicker> style() {
        return new StylingRes.Delegation<>(new TabPicker());
    }
}
