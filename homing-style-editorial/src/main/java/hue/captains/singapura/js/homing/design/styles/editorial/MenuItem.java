package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The menu item in editorial: one choice of a menu - flat, tinted under the hand and where the menu's cursor is, faded
 * while off. Whole: every property its look has (D9).
 */
public record MenuItem() implements CastingStyle<ColorScheme.Base, Editorial, HouseItems.MenuItem, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.MenuItem> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Border(), new HouseColours.Accent(), new HouseColours.AccentWashSoft(),
                new HouseColours.AccentWashStrong());
    }
}
