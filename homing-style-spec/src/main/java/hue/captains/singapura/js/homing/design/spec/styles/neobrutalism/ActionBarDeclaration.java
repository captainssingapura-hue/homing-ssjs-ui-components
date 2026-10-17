package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

import java.util.List;

/** neo-brutalism's declaration of action-bar: its own implementation, and a spec for each of its slots. */
public record ActionBarDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseContainers.ActionBar, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.ActionBar, RoleRoot> own() { return new ActionBar(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseContainers.ActionBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.ActionBar.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseDoing.Go.INSTANCE, new Target.Impl<>(new PrimaryButtonGo())),
                new PartSpec.SameRole<>(HouseContainers.ActionBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE, new Target.Impl<>(new PlainButtonAction()))));
    }
}
