package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** neumorphism's implementation of list-row_choice: as its component standalone, by delegation. */
public record ListRowChoice() implements RootDelegation<BaseScheme, Neumorphism, HouseItems.ListRow, HouseDoing.Choice> {
    @Override public CastingImplementation<BaseScheme, Neumorphism, HouseItems.ListRow, RoleRoot> to() { return new ListRow(); }
}
