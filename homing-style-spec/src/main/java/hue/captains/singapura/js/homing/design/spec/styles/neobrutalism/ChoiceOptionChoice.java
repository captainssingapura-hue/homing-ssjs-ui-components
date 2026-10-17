package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neo-brutalism's look of choice-option_choice: as its component on its own, by delegation. */
public record ChoiceOptionChoice() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new ChoiceOption());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseItems.ChoiceOption.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new CaptionNote())));
    }
}
