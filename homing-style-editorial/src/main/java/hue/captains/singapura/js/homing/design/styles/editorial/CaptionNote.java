package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Editorial's caption note: as its caption on its own, by delegation. */
public record CaptionNote() implements CastingStyle<ColorScheme.Base, Editorial, HouseText.Caption, HouseSaying.Note> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Caption> style() {
        return new StylingRes.Delegation<>(new Caption());
    }
}
