package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The header cell in the base layer: a document table's cell heading its column, a container - its room, and nothing of
 * the table's lines nor of its words' look. Whole: every property its look has (D9).
 */
public record HeaderCell() implements CastingStyle<BaseScheme, Base, HouseContainers.HeaderCell, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.HeaderCell> style() {
        return new StylingRes.CssContent<>();
    }
}
