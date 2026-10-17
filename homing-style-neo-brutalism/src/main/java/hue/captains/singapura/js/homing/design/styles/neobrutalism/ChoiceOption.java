package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The choice option in neo-brutalism: one choice of a setting - tinted under the hand, ringed by the focus, filled
 * while picked. Whole: every property its look has (D9).
 */
public record ChoiceOption()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseItems.ChoiceOption> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(),
                new BaseColours.Ring(), new BaseColours.AccentWashSoft());
    }
}
