package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Sketchy's list row choice: as its list row on its own, by delegation. */
public record ListRowChoice()
        implements CastingStyle<BaseScheme, Sketchy, HouseItems.ListRow, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, HouseItems.ListRow> style() {
        return new StylingRes.Delegation<>(new ListRow());
    }
}
