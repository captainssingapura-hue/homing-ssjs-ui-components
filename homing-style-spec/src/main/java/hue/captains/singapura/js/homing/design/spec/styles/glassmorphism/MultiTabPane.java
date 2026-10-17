package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** glassmorphism's look of multi-tab-pane: base's, by delegation. */
public record MultiTabPane() implements CastingStylePerRole<BaseScheme, Glassmorphism, HousePanes.MultiTabPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HousePanes.MultiTabPane, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MultiTabPane());
    }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HousePanes.MultiTabPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanes.MultiTabPane.INSTANCE, HousePanes.TabPicker.INSTANCE, HouseDoing.Picker.INSTANCE, new TabPickerPicker()),
                new PartSpec.Cast<>(HousePanes.MultiTabPane.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new CaptionNote())));
    }
}
