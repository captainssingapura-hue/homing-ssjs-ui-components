package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** neo-brutalism's look of dialog: base's, by delegation. */
public record Dialog() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.Dialog, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.Dialog, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Dialog());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseContainers.Dialog> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HouseContainers.Dialog.INSTANCE, HouseContainers.ActionBar.INSTANCE, HouseShaping.Actions.INSTANCE, new ActionBar())));
    }
}
