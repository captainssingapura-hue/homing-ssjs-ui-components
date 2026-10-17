package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** sketchy's look of choice-option_palette: as its component on its own, by delegation. */
public record ChoiceOptionPalette() implements CastingStylePerRole<BaseScheme, Sketchy, HouseItems.ChoiceOption, HouseDoing.Palette> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseItems.ChoiceOption, HouseDoing.Palette> style() {
        return new StylingRes.RootDelegation<>(new ChoiceOption());
    }

    @Override public PartsSpec<BaseScheme, Sketchy, HouseItems.ChoiceOption> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseItems.ChoiceOption.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new CaptionNote())));
    }
}
