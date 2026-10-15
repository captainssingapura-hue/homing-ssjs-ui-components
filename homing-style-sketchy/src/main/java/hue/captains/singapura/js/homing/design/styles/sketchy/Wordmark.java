package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The wordmark in sketchy: a name set as the mark of whom it belongs to. Whole: every property its look has (D9). */
public record Wordmark() implements CastingStyle<ColorScheme.Base, Sketchy, HouseText.Wordmark, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Wordmark> style() {
        return new StylingRes.CssContent<>();
    }
}
