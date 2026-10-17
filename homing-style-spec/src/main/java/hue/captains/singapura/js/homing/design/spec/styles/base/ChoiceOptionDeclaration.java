package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** base's declaration of choice-option: its own implementation, and a spec for each of its slots. */
public record ChoiceOptionDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseItems.ChoiceOption, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HouseItems.ChoiceOption, RoleRoot> own() { return new ChoiceOption(); }

    @Override public PartsSpec<BaseScheme, Base, HouseItems.ChoiceOption> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseItems.ChoiceOption.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new Target.Impl<>(new CaptionNote()))));
    }
}
