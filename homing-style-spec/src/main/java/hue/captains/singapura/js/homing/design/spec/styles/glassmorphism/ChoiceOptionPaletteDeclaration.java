package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** glassmorphism's declaration of choice-option_palette: its own implementation, and a spec for each of its slots. */
public record ChoiceOptionPaletteDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HouseItems.ChoiceOption, HouseDoing.Palette> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseItems.ChoiceOption, HouseDoing.Palette> own() { return new ChoiceOptionPalette(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseItems.ChoiceOption> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseItems.ChoiceOption.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new Target.Impl<>(new CaptionNote()))));
    }
}
