package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The tick in neo-brutalism: a scale's tick - a short line in the colour it is given. Whole: every property its look
 * has (D9).
 */
public record Tick() implements CastingStyle<BaseScheme, NeoBrutalism, HouseMarks.Tick, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Tick> style() {
        return new StylingRes.CssContent<>(new BaseColours.Muted());
    }
}
