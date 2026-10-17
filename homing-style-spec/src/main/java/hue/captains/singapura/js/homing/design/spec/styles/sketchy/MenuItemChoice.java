package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** sketchy's implementation of menu-item_choice: as its component standalone, by delegation. */
public record MenuItemChoice() implements CastingStylePerRole<BaseScheme, Sketchy, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseItems.MenuItem, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new MenuItem());
    }
}
