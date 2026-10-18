package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** base's implementation of menu-item_choice: as its component standalone, by delegation. */
public record MenuItemChoice() implements RootDelegation<BaseScheme, Base, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public CastingImplementation<BaseScheme, Base, HouseItems.MenuItem, RoleRoot> to() { return new MenuItem(); }
}
