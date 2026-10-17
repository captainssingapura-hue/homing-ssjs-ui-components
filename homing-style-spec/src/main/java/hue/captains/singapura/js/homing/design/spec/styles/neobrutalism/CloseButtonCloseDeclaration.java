package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** neo-brutalism's declaration of close-button_close: its own implementation, and a spec for each of its slots. */
public record CloseButtonCloseDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseControls.CloseButton, HouseDoing.Close> {
    @Override public CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.CloseButton, HouseDoing.Close> own() { return new CloseButtonClose(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseControls.CloseButton> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseControls.CloseButton.INSTANCE, HouseIcons.CloseIcon.INSTANCE, HouseShaping.Face.INSTANCE)));
    }
}
