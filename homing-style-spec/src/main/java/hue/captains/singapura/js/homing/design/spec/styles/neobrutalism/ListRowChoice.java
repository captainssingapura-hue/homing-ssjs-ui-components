package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** neo-brutalism's look of list-row_choice: as its component on its own, by delegation. */
public record ListRowChoice() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseItems.ListRow, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseItems.ListRow, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new ListRow());
    }
}
