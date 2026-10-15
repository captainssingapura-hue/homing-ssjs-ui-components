package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The detent in editorial: a notch a value may snap to - a line across the groove, shown while what rests on it is in
 * the hand. Whole: every property its look has (D9).
 */
public record Detent() implements CastingStyle<ColorScheme.Base, Editorial, HouseMarks.Detent, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Detent> style() {
        return new StylingRes.CssContent<>(new HouseColours.Muted());
    }
}
