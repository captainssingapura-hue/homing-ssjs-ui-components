package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The list row in the base layer: one row of a list - tinted under the hand, filled while picked, washed while current,
 * marked where the keys are. Whole: every property its look has (D9).
 */
public record ListRow() implements CastingStyle<ColorScheme.Base, Base, HouseItems.ListRow, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.ListRow> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.OnInverted(), new HouseColours.Border(), new HouseColours.Title(),
                new HouseColours.Accent(), new HouseColours.AccentWashLight(), new HouseColours.AccentWashSoft(),
                new HouseColours.KeysCandidate(), new HouseColours.KeysLent());
    }
}
