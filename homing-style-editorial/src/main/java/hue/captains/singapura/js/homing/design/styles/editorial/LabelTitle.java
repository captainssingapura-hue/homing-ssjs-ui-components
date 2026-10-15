package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The label as title in editorial: words that name what they stand by. Whole: every property its look has (D9). */
public record LabelTitle() implements CastingStyle<ColorScheme.Base, Editorial, HouseText.Label, HouseSaying.Title> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Label> style() {
        return new StylingRes.CssContent<>();
    }
}
