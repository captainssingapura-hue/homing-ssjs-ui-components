package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** neumorphism's look of list-row_choice: as its component on its own, by delegation. */
public record ListRowChoice() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseItems.ListRow, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseItems.ListRow, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new ListRow());
    }
}
