package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The data cell in the base layer: a document table's cell holding one fact, a container - its room, and nothing of the
 * table's lines nor of its words' look. Whole: every property its look has (D9).
 */
public record DataCell() implements CastingStyle<BaseScheme, Base, HouseContainers.DataCell, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.DataCell> style() {
        return new StylingRes.CssContent<>();
    }
}
