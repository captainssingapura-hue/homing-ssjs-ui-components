package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** NeoFuturism's menu item choice: as its menu item on its own, by delegation. */
public record MenuItemChoice()
        implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.MenuItem> style() {
        return new StylingRes.Delegation<>(new MenuItem());
    }
}
