package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

/** neo-brutalism's implementation of choice-widget: the base's, by delegation. */
public record ChoiceWidget() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HousePreferences.ChoiceWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HousePreferences.ChoiceWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceWidget());
    }
}
