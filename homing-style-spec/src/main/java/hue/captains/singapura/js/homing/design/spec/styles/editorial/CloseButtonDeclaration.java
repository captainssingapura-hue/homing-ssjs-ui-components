package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** editorial's declaration of close-button: its own implementation, and a spec for each of its slots. */
public record CloseButtonDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HouseControls.CloseButton, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Editorial, HouseControls.CloseButton, RoleRoot> own() { return new CloseButton(); }

    @Override public PartsSpec<BaseScheme, Editorial, HouseControls.CloseButton> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseControls.CloseButton.INSTANCE, HouseIcons.CloseIcon.INSTANCE, HouseShaping.Face.INSTANCE)));
    }
}
