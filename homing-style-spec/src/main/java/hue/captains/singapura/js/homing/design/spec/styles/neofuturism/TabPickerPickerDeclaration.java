package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-futurism's declaration of tab-picker_picker: its own implementation, and a spec for each of its slots. */
public record TabPickerPickerDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HousePanes.TabPicker, HouseDoing.Picker> {
    @Override public CastingStylePerRole<BaseScheme, NeoFuturism, HousePanes.TabPicker, HouseDoing.Picker> own() { return new TabPickerPicker(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HousePanes.TabPicker> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePanes.TabPicker.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Prompt.INSTANCE, new Target.Impl<>(new KickerPrompt())),
                new PartSpec.SameRole<>(HousePanes.TabPicker.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Impl<>(new PlainButtonChoice())),
                new PartSpec.SameRole<>(HousePanes.TabPicker.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Cancel.INSTANCE, new Target.Impl<>(new PlainButtonCancel()))));
    }
}
