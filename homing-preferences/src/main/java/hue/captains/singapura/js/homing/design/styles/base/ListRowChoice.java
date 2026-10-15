package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Base's list row choice: as its list row on its own, by delegation. */
public record ListRowChoice() implements CastingStyle<ColorScheme.Base, Base, HouseItems.ListRow, HouseDoing.Choice> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.ListRow> style() {
        return new StylingRes.Delegation<>(new ListRow());
    }
}
