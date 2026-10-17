package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import java.util.List;

/** neo-brutalism's look of choice-widget: base's, by delegation. */
public record ChoiceWidget() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HousePreferences.ChoiceWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HousePreferences.ChoiceWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceWidget());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HousePreferences.ChoiceWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.ChoiceWidget.INSTANCE, HouseItems.ChoiceOption.INSTANCE, HouseDoing.Choice.INSTANCE, new ChoiceOptionChoice())));
    }
}
