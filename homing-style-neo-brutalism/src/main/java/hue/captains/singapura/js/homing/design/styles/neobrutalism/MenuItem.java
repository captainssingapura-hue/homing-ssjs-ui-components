package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The menu item in neo-brutalism: one choice of a menu - flat, tinted under the hand and where the menu's cursor is,
 * faded while off. Whole: every property its look has (D9).
 */
public record MenuItem() implements CastingStyle<BaseScheme, NeoBrutalism, HouseItems.MenuItem, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseItems.MenuItem> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Border(), new HouseColours.Accent(), new HouseColours.AccentWashSoft(),
                new HouseColours.AccentWashStrong());
    }
}
