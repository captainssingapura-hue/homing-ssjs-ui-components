package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The list row in editorial: one row of a list - tinted under the hand, filled while picked, washed while current,
 * marked where the keys are. Whole: every property its look has (D9).
 */
public record ListRow() implements CastingStyle<BaseScheme, Editorial, HouseItems.ListRow, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseItems.ListRow> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.OnInverted(), new HouseColours.Border(), new HouseColours.Title(),
                new HouseColours.Accent(), new HouseColours.AccentWashLight(), new HouseColours.AccentWashSoft(),
                new HouseColours.KeysCandidate(), new HouseColours.KeysLent());
    }
}
