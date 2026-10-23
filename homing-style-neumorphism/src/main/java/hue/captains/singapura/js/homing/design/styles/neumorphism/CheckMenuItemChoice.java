package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Neumorphism's check menu item choice: as its check menu item on its own, by delegation. */
public record CheckMenuItemChoice()
        implements CastingStyle<BaseScheme, Neumorphism, HouseItems.CheckMenuItem, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, HouseItems.CheckMenuItem> style() {
        return new StylingRes.Delegation<>(new CheckMenuItem());
    }
}
