package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The boundary line in the base layer: the line where two regions meet - in the border's colour, the accent under the
 * hand, focused and in the hand; its cursor sizing's. Whole: every property its look has (D9).
 */
public record BoundaryLine() implements CastingStyle<BaseScheme, Base, HouseMarks.BoundaryLine, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.BoundaryLine> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Border(), new BaseColours.LineHovered(), new BaseColours.Accent());
    }
}
