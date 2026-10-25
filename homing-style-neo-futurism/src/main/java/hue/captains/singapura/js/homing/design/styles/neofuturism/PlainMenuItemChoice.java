package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** NeoFuturism's plain menu item choice: as its plain menu item on its own, by delegation. */
public record PlainMenuItemChoice()
        implements CastingStyle<BaseScheme, NeoFuturism, HouseItems.PlainMenuItem, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, HouseItems.PlainMenuItem> style() {
        return new StylingRes.Delegation<>(new PlainMenuItem());
    }
}
