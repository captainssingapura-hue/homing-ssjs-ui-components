package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Neumorphism's menu item choice: as its menu item on its own, by delegation. */
public record MenuItemChoice()
        implements CastingStyle<BaseScheme, Neumorphism, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, HouseItems.MenuItem> style() {
        return new StylingRes.Delegation<>(new MenuItem());
    }
}
