package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** NeoBrutalism's list row choice: as its list row on its own, by delegation. */
public record ListRowChoice()
        implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseItems.ListRow, HouseDoing.Choice> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.ListRow> style() {
        return new StylingRes.Delegation<>(new ListRow());
    }
}
