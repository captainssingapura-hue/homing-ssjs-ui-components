package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** neo-brutalism's implementation of menu-item_choice: as its component standalone, by delegation. */
public record MenuItemChoice() implements RootDelegation<BaseScheme, NeoBrutalism, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseItems.MenuItem, RoleRoot> to() { return new MenuItem(); }
}
