package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Sketchy's menu item choice: as its menu item on its own, by delegation. */
public record MenuItemChoice()
        implements CastingStyle<ColorScheme.Base, Sketchy, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.MenuItem> style() {
        return new StylingRes.Delegation<>(new MenuItem());
    }
}
