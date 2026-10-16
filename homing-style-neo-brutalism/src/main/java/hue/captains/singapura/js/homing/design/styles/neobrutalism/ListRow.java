package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The list row in neo-brutalism: one row of a list - tinted under the hand, filled while picked, washed while current,
 * marked where the keys are. Whole: every property its look has (D9).
 */
public record ListRow() implements CastingStyle<BaseScheme, NeoBrutalism, HouseItems.ListRow, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseItems.ListRow> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(),
                new BaseColours.Accent(), new BaseColours.AccentWashLight(), new BaseColours.AccentWashSoft(),
                new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
