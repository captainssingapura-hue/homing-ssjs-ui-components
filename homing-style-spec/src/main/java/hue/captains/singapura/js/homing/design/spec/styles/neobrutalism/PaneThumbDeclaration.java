package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-brutalism's declaration of pane-thumb: its own implementation, and a spec for each of its slots. */
public record PaneThumbDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseControls.PaneThumb, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.PaneThumb, RoleRoot> own() { return new PaneThumb(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseControls.PaneThumb> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.PaneThumb.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Name.INSTANCE, new Target.Impl<>(new KickerName()))));
    }
}
