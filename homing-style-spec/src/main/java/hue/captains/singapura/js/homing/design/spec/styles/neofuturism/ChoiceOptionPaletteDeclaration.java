package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-futurism's declaration of choice-option_palette: its own implementation, and a spec for each of its slots. */
public record ChoiceOptionPaletteDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseItems.ChoiceOption, HouseDoing.Palette> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseItems.ChoiceOption, HouseDoing.Palette> own() { return new ChoiceOptionPalette(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseItems.ChoiceOption> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseItems.ChoiceOption.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new Target.Impl<>(new CaptionNote()))));
    }
}
