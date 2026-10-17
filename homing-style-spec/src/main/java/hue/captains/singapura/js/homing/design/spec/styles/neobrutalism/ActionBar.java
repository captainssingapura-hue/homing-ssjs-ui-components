package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** neo-brutalism's look of action-bar: CSS of its own. */
public record ActionBar() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.ActionBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.ActionBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseContainers.ActionBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.ActionBar.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseDoing.Go.INSTANCE, new PrimaryButtonGo()),
                new PartSpec.Cast<>(HouseContainers.ActionBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE, new PlainButtonAction())));
    }
}
