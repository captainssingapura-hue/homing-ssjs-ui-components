package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** base's look of multi-tab-pane: CSS of its own. */
public record MultiTabPane() implements CastingStylePerRole<BaseScheme, Base, HousePanes.MultiTabPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePanes.MultiTabPane, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Accent(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }

    @Override public PartsSpec<BaseScheme, Base, HousePanes.MultiTabPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanes.MultiTabPane.INSTANCE, HousePanes.TabPicker.INSTANCE, HouseDoing.Picker.INSTANCE, new TabPickerPicker()),
                new PartSpec.Cast<>(HousePanes.MultiTabPane.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new CaptionNote())));
    }
}
