package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The tick in neo-brutalism: a scale's tick - a short line in the colour it is given. Whole: every property its look
 * has (D9).
 */
public record Tick() implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseMarks.Tick, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Tick> style() {
        return new StylingRes.CssContent<>(new HouseColours.Muted());
    }
}
