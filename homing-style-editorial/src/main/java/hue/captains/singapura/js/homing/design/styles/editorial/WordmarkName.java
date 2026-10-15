package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Editorial's wordmark name: as its wordmark on its own, by delegation. */
public record WordmarkName()
        implements CastingStyle<ColorScheme.Base, Editorial, HouseText.Wordmark, HouseSaying.Name> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Wordmark> style() {
        return new StylingRes.Delegation<>(new Wordmark());
    }
}
